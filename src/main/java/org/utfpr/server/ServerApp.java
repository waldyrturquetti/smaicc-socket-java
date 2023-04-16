package org.utfpr.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.utfpr.server.infra.db.DB;
import org.utfpr.server.util.Gateway;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.HashMap;

public class ServerApp extends Thread {

    private static boolean serverContinue = true;
    private final Socket clientSocket;

    public static void main(String[] args) throws Exception {
        startSocket();
    }

    private static void startSocket() throws Exception {
        ServerSocket serverSocket = null;
        DB.getConnection();

        try {
            serverSocket = new ServerSocket(10008);
            System.out.println("Connection Socket Created");
            try {
                while (serverContinue) {
                    serverSocket.setSoTimeout(150000);
                    System.out.println("Waiting for Connection");
                    try {
                        new ServerApp(serverSocket.accept());
                    } catch (SocketTimeoutException ste) {
                        System.out.println("Timeout Occurred");
                        serverContinue = false;
                    }
                }
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port: 10008.");
            System.exit(1);
        } finally {
            try {
                System.out.println("Closing Server Connection Socket");
                assert serverSocket != null;
                serverSocket.close();
            } catch (IOException e) {
                System.err.println("Could not close port: 10008.");
                System.exit(1);
            }
        }
    }

    private ServerApp(Socket clientSocket) {
        this.clientSocket = clientSocket;
        super.start();
    }

    public void run() {
        System.out.println ("New Communication Thread Started");

        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String message = in.readLine();
            HashMap<String, Object> json = this.convertStringToHashMap(message);
            Gateway.chooseOperation(json);

            out.println(message);

            out.close();
            in.close();
            clientSocket.close();

        } catch (IOException e) {
            System.err.println("Problem with Communication Server");
            System.exit(1);
        }
    }

    private HashMap<String, Object> convertStringToHashMap(String message) throws JsonProcessingException {
        return new ObjectMapper().readValue(message, new TypeReference<>() {});
    }
}
