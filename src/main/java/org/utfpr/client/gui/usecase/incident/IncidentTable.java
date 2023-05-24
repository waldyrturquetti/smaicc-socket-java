package org.utfpr.client.gui.usecase.incident;

import org.utfpr.common.dto.incident.getIncidents.IncidentData;

import javax.swing.*;
import java.util.List;

public class IncidentTable {

    private final JFrame frame;

    public IncidentTable() {
        this.frame = new JFrame("Incidentes");
    }

    public JTable createTable(List<IncidentData> incidentDataList)
    {
        String[] columnNames = {"First Name", "Last Name"};
        Object[][] data = {{"Kathy", "Smith"},{"John", "Doe"}};
        JTable table = new JTable(data, columnNames);
        table.setFillsViewportHeight(true);
        return table;
    }
    public void buildScreen(List<IncidentData> incidentDataList){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTable table = createTable(incidentDataList);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.getContentPane().add(scrollPane);
        frame.pack();
        frame.setVisible(true);
    }
}
