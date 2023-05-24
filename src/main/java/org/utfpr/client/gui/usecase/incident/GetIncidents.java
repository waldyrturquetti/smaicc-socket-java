package org.utfpr.client.gui.usecase.incident;

import org.utfpr.client.util.ComboBoxValues;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.util.ArrayList;

public class GetIncidents extends JFrame {
    private JPanel getIncidents;
    private JTextField dateField;
    private JTextField cityField;
    private JButton getButton;
    private JComboBox<String> statesComboBox;

    public GetIncidents(){
        this.getButton.addActionListener(e -> new IncidentTable().buildScreen(new ArrayList<>()));
    }

    public void buildScreen() {
        this.setContentPane(this.getIncidents);
        this.setTitle("Buscar Incidentes");
        this.setSize(350, 300);
        this.setStatesInComboBox();
        this.setVisible(true);
    }

    private void setStatesInComboBox() {
        String[] states = ComboBoxValues.getStateValues();
        for (String state : states) {
            this.statesComboBox.addItem(state);
        }
    }
}
