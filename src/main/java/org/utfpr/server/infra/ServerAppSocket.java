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
    private final Socket socket;

    public static void startSocket(Integer port) throws SQLException, DbException {
        ServerSocket serverSocket = null;
        Database.getConnection();

        try {
            InetAddress ipAddress = InetAddress.getByName("0.0.0.0");
            serverSocket = new ServerSocket(port, 0, ipAddress);
            System.out.println("Conexão do Socket criada.");
            try {
                while (serverContinue) {
                    System.out.println("Esperando conexão.");
                    new ServerAppSocket(serverSocket.accept());
                }
            } catch (IOException e) {
                System.err.println("Accept falhado.");
                System.exit(1);
            }
        } catch (IOException e) {
            System.err.println("Não foi possível conectar com a Porta: " + port);
            System.exit(1);
        } finally {
            try {
                System.out.println("Fechando a conexão do Socket.");
                Database.closeConnection();
                assert serverSocket != null;
                serverSocket.close();
            } catch (IOException e) {
                System.err.println("Não foi possível fechar a porta: " + port);
                System.exit(1);
            }
        }
    }

    public static void closeSocket() {
        serverContinue = false;
    }

    private ServerAppSocket(Socket clientSocket) {
        this.socket = clientSocket;
        super.start();
    }

    /**
     *  In the project specifications it was defined that the connection between Server and Client would be as follows:
     *  The socket will be created and destroyed on each operation.
     **/
    public void run() {
        System.out.println("Nova Thread de comunição iniciada.");

        try {
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

        } catch (IOException e) {
            System.err.println("Problem communicating with the Client.");
        }
    }
}
