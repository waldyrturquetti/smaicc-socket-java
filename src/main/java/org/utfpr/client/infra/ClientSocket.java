package org.utfpr.client.infra;

import org.utfpr.client.exception.ProblemWithServerConnectionException;
import org.utfpr.common.dto.Data;
import org.utfpr.common.util.Convert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

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
            throw new ProblemWithServerConnectionException("Problema para se conectar com o Servidor. Verifique se a Porta está correta.");
        }
    }

    public static void sendMessage(Data data) throws IOException {
        HashMap<String, Object> json = Convert.convertDataToHashMap(data);
        String message = Convert.convertHashMapToString(json);
        out.println(message);
    }

    public static Data receiveMessage(Data typeOfData) throws IOException {
        HashMap<String, Object> jsonReturned = Convert.convertStringToHashMap(in.readLine());
        return Convert.convertHashMapToData(jsonReturned, typeOfData);
    }
}


