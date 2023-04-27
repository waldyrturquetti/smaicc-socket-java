package org.utfpr.server;

import org.utfpr.server.infra.Database;
import org.utfpr.server.util.Gateway;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class ServerApp extends Thread {

    private static boolean serverContinue = true;
    private final Socket clientSocket;

    public static void main(String[] args) throws SQLException {
        startSocket();
    }

    private static void startSocket() throws SQLException {
        ServerSocket serverSocket = null;
        Database.getConnection();

        try {
            serverSocket = new ServerSocket(10008);
            System.out.println("Connection Socket Created");
            try {
                while (serverContinue) {
                    System.out.println("Waiting for Connection");
                    new ServerApp(serverSocket.accept());
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
                Database.closeConnection();
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
        System.out.println ("New Communication Thread Started.");

        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String incomingMessage = in.readLine();
            String outgoingMessage = Gateway.chooseOperation(incomingMessage);

            out.println(outgoingMessage);

            out.close();
            in.close();
            clientSocket.close();

        } catch (IOException e) {
            System.err.println("Problem with Communication Server.");
            System.exit(1);
        }
    }
}
