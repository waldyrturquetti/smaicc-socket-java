package org.utfpr.client.gui.usecase;

import javax.swing.*;

public class CreateIncident extends JFrame {
    private JPanel createIncidentPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JComboBox<String> comboBox1;
    private JComboBox<String> comboBox2;
    private JTextField textField5;

    public CreateIncident() {}

    public void buildScreen() {
        this.setContentPane(this.createIncidentPanel);
        this.setTitle("Criar Usário");
        this.setVisible(true);
        this.setSize(350, 300);
    }
}
