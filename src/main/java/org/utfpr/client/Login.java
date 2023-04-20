package org.utfpr.client;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Login extends JFrame {

    private JPanel login;
    private JTextField textField1;
    private JButton send;
    private PrintWriter out;

    public Login() {
        send.addActionListener(s -> {
            this.startConnect();
            out.println(this.textField1.getText().trim());
        });
    }

    public void buildScreen() {
        this.setContentPane(this.login);
        this.setTitle("Login");
        this.setVisible(true);
        this.setSize(350, 200);
    }

    private void startConnect() {
        String serverHostname = "127.0.0.1";

        System.out.println ("Attemping to connect to host " + serverHostname + " on port 10008.");

        try {
            Socket echoSocket = new Socket(serverHostname, 10008);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + serverHostname);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for " + "the connection to: " + serverHostname);
            System.exit(1);
        }
    }
}