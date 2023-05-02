package org.utfpr.client.gui;

import javax.swing.*;

public class ErrorScreen extends JFrame {

    private JPanel errorPanel;
    private JButton okButton;
    private JLabel errorText;

    public ErrorScreen() {
        this.okButton.addActionListener(e -> this.setVisible(false));
    }

    public void buildScreen(String error) {
        this.setContentPane(this.errorPanel);
        this.setTitle("Erro");
        this.setVisible(true);
        this.setSize(350, 200);
        this.errorText.setText(error);
    }
}
