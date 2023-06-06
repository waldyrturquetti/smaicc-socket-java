package org.utfpr.client.gui.usecase.incident;

import org.utfpr.common.dto.incident.IncidentData;

import javax.swing.*;
import java.util.List;

public class IncidentTable {

    private final JFrame frame;

    public IncidentTable() {
        this.frame = new JFrame("Incidentes");
    }

    public JTable createTable(List<IncidentData> incidentDataList)
    {
        String[] columnNames = {"Data", "Hora", "Estado", "Cidade", "Bairro", "Rua", "Tipo de Incidente"};
        Object[][] data = incidentDataList.stream().map(IncidentData::convertToObjectArray).toArray(Object[][]::new);
        JTable table = new JTable(data, columnNames);
        table.setFillsViewportHeight(true);
        return table;
    }
    public void buildScreen(List<IncidentData> incidentDataList){
        JTable table = createTable(incidentDataList);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.getContentPane().add(scrollPane);
        frame.pack();
        frame.setSize(700, 600);
        frame.setVisible(true);
    }
}
