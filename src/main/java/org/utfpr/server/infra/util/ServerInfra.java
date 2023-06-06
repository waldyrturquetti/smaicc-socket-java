package org.utfpr.server.infra.util;

import org.utfpr.common.gui.LogScreen;
import org.utfpr.server.infra.thread.ServerSocketThread;

import java.net.ServerSocket;

public class ServerInfra {

    private static ServerSocketThread serverSocketThread;

    private static ServerSocket serverSocket;

    private static LogScreen serverLog;

    public static ServerSocketThread getServerSocketThread() {
        return serverSocketThread;
    }

    public static void setServerSocketThread(ServerSocketThread serverSocketThread) {
        ServerInfra.serverSocketThread = serverSocketThread;
    }

    public static ServerSocket getServerSocket() {
        return serverSocket;
    }

    public static void setServerSocket(ServerSocket serverSocket) {
        ServerInfra.serverSocket = serverSocket;
    }

    public static LogScreen getServerLog() {
        return serverLog;
    }

    public static void setServerLog(LogScreen serverLog) {
        ServerInfra.serverLog = serverLog;
    }
}
