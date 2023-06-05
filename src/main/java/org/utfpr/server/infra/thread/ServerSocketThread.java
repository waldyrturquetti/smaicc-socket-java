package org.utfpr.server.infra.thread;

import org.utfpr.server.exception.DbException;
import org.utfpr.server.gui.ServerLog;
import org.utfpr.server.infra.database.Database;
import org.utfpr.server.infra.util.ServerInfra;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.sql.SQLException;

public class ServerSocketThread extends Thread {
    private static boolean serverContinue;
    private final ServerSocket serverSocket;
    private final ServerLog serverLog;

    public static void startSocket(Integer port, ServerLog serverLog) throws SQLException, DbException {
        Database.getConnection();
        serverContinue = true;

        try {
            InetAddress ipAddress = InetAddress.getByName("0.0.0.0");
            ServerInfra.setServerSocket(new ServerSocket(port, 0, ipAddress));
            ServerInfra.setServerSocketThread(new ServerSocketThread(ServerInfra.getServerSocket(), serverLog));
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.err.println("Não foi possível Iniciar o Socket do Servidor e conectar com a Porta: " + port);
            System.exit(1);
        }
    }

    public static void closeSocket() {
        try {
            serverContinue = false;

            if (ServerInfra.getServerSocketThread() != null) {
                ServerInfra.getServerSocketThread().interrupt();
            }

            if (ServerInfra.getServerSocket() != null && !ServerInfra.getServerSocket().isClosed()) {
                ServerInfra.getServerSocket().close();
            }

            Database.closeConnection();

        } catch (Exception e) {
            System.err.println("Não foi possível PARAR o SERVIDOR corretamente:");
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    private ServerSocketThread(ServerSocket serverSocket, ServerLog serverLog) {
        this.serverSocket = serverSocket;
        this.serverLog = serverLog;
        super.start();
    }

    /**
     *  In the project specifications it was defined that the connection between Server and Client would be as follows:
     *  The socket will be created and destroyed on each operation.
     **/
    public void run() {
        try {
            while (serverContinue) {
                new SocketThread(serverSocket.accept(), this.serverLog);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
