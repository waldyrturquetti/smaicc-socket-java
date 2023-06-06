package org.utfpr.server.infra.thread;

import org.utfpr.common.gui.LogScreen;
import org.utfpr.server.util.Gateway;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketThread extends Thread {

    private final Socket socket;
    private final LogScreen serverLog;

    public SocketThread(Socket socket, LogScreen serverLog) {
        this.socket = socket;
        this.serverLog = serverLog;
        super.start();
    }

    /**
     *  In the project specifications it was defined that the connection between Server and Client would be as follows:
     *  The socket will be created and destroyed on each operation.
     **/
    public void run() {
        try {
            PrintWriter out = new PrintWriter(this.socket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

            String incomingMessage;

            while ((incomingMessage = in.readLine()) != null) {
                System.out.println("*SERVIDOR* Recebido: " + incomingMessage);
                String outgoingMessage = Gateway.chooseOperation(incomingMessage);
                System.out.println("*SERVIDOR* Enviado: " + outgoingMessage);
                this.serverLog.setRowInTable(this.socket.getInetAddress().toString(), this.socket.getPort(), incomingMessage, outgoingMessage);
                out.println(outgoingMessage);
            }

            out.close();
            in.close();
            this.socket.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
