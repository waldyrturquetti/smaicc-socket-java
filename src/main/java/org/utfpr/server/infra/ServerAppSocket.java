package org.utfpr.server.infra;

import org.utfpr.server.util.Gateway;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class ServerAppSocket extends Thread {

    private static boolean serverContinue = true;
    private final Socket socket;

    public static void startSocket(Integer port) throws SQLException {
        ServerSocket serverSocket = null;
        Database.getConnection();

        try {
            InetAddress ipAddress = InetAddress.getByName("0.0.0.0");
            serverSocket = new ServerSocket(port, 0, ipAddress);
            System.out.println("Connection Socket Created");
            try {
                while (serverContinue) {
                    System.out.println("Waiting for Connection");
                    new ServerAppSocket(serverSocket.accept());
                }
            } catch (IOException e) {
                System.err.println("Accept failed");
                System.exit(1);
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + port);
            System.exit(1);
        } finally {
            try {
                System.out.println("Closing Server Connection Socket");
                Database.closeConnection();
                assert serverSocket != null;
                serverSocket.close();
            } catch (IOException e) {
                System.err.println("Could not close port:" + port);
                System.exit(1);
            }
        }
    }

    private ServerAppSocket(Socket clientSocket) {
        this.socket = clientSocket;
        super.start();
    }

    public void run() {
        System.out.println ("New Communication Thread Started.");

        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String incomingMessage;

            while ((incomingMessage = in.readLine()) != null) {
                String outgoingMessage = Gateway.chooseOperation(incomingMessage);
                out.println(outgoingMessage);
            }

            out.close();
            in.close();
            socket.close();

        } catch (IOException e) {
            System.err.println("Problem communicating with the Client.");
        }
    }
}
