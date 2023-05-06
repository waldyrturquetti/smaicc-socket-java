package org.utfpr.client.gui;

import javax.swing.*;

public class ErrorScreen extends JFrame {

    private JPanel errorPanel;
    private JButton okButton;
    private JTextPane textPane;

    public ErrorScreen() {
        this.okButton.addActionListener(e -> this.setVisible(false));
    }

    public void buildScreen(String error) {
        this.setContentPane(this.errorPanel);
        this.setTitle("Erro");
        this.setVisible(true);
        this.setSize(450, 200);
        this.textPane.setText(error);
    }
}
