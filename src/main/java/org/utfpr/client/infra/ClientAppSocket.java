package org.utfpr.client.infra;

import org.utfpr.client.exception.ProblemWithServerConnectionException;
import org.utfpr.client.exception.ServerFailureException;
import org.utfpr.common.dto.Data;
import org.utfpr.common.util.Convert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

public class ClientAppSocket {

    private static PrintWriter out;
    private static BufferedReader in;
    private static Socket socket;
    private static String serverHostname;
    private static Integer port;

    private static void startConnect() {

        System.out.println ("Attemping to connect to host " + serverHostname + " on port " + port);

        try {
            socket = new Socket(serverHostname, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e) {
            throw new ProblemWithServerConnectionException("Host não encontrado.");
        } catch (IOException e) {
            throw new ProblemWithServerConnectionException("Problema para se conectar com o Servidor. Verifique se a Porta está correta.");
        }
    }

    private static void closeConnect() throws IOException {
        out.close();
        in.close();
        socket.close();
    }

    public static void sendMessage(Data data)
    {
        try {
            startConnect();
            HashMap<String, Object> json = Convert.convertDataToHashMap(data);
            String outgoingMessage = Convert.convertHashMapToString(json);
            out.println(outgoingMessage);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static Data receiveMessage(Class<? extends Data> typeOfData) throws IOException {
        String incomingMessage = in.readLine();
        if (incomingMessage == null){
            throw new ServerFailureException();
        }
        HashMap<String, Object> jsonReturned = Convert.convertStringToHashMap(incomingMessage);
        closeConnect();
        return Convert.convertHashMapToData(jsonReturned, typeOfData);
    }

    public static void setServerHostname(String serverHostname) {
        ClientAppSocket.serverHostname = serverHostname;
    }

    public static void setPort(Integer port) {
        ClientAppSocket.port = port;
    }
}


