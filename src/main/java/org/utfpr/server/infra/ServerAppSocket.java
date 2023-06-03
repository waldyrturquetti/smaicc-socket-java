package org.utfpr.server.infra;

import org.utfpr.server.exception.DbException;
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
    private final ServerSocket serverSocket;

    public static void startSocket(Integer port) throws SQLException, DbException {
        Database.getConnection();

        try {
            InetAddress ipAddress = InetAddress.getByName("0.0.0.0");
            ServerSocket serverSocket = new ServerSocket(port, 0, ipAddress);
            new ServerAppSocket(serverSocket);
        } catch (IOException e) {
            System.err.println("Não foi possível conectar com a Porta: " + port);
            System.exit(1);
        }
    }

    public static void closeSocket() {
        try {
            serverContinue = false;
            Database.closeConnection();
        } catch (Exception e) {
            System.err.println("Não foi possível PARAR o SERVIDOR corretamente.");
            System.exit(1);
        }
    }

    private ServerAppSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        super.start();
    }

    /**
     *  In the project specifications it was defined that the connection between Server and Client would be as follows:
     *  The socket will be created and destroyed on each operation.
     **/
    public void run() {
        try {
            while (serverContinue) {
                Socket socket = serverSocket.accept();

                PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String incomingMessage;

                while ((incomingMessage = in.readLine()) != null) {
                    System.out.println("*SERVIDOR* Recebido: " + incomingMessage);
                    String outgoingMessage = Gateway.chooseOperation(incomingMessage);
                    System.out.println("*SERVIDOR* Enviado: " + outgoingMessage);
                    out.println(outgoingMessage);
                }

                out.close();
                in.close();
                socket.close();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
