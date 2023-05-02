package org.utfpr.client.infra;

import org.utfpr.client.exception.ProblemWithServerConnectionException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSocket {

    private static PrintWriter out;
    private static BufferedReader in;

    public static void startConnect(String serverHostname, Integer port) {

        System.out.println ("Attemping to connect to host " + serverHostname + " on port " + port);

        try {
            Socket echoSocket = new Socket(serverHostname, port);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            throw new ProblemWithServerConnectionException("Host não encontrado.");
        } catch (IOException e) {
            throw new ProblemWithServerConnectionException();
        }
    }
}
