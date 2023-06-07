package org.utfpr.client.gui.usecase.incident;

import org.utfpr.common.dto.incident.IncidentData;
import org.utfpr.common.gui.interfaces.Gui;

import javax.swing.*;
import java.util.List;

public class IncidentTable implements Gui {

    private final JFrame frame;

    private final JTable table;

    public IncidentTable(List<IncidentData> incidentDataList) {
        this.frame = new JFrame("Incidentes");
        this.table = createTable(incidentDataList);
    }

    @Override
    public void buildScreen() {
        JScrollPane scrollPane = new JScrollPane(this.table);
        frame.getContentPane().add(scrollPane);
        frame.pack();
        frame.setSize(700, 600);
        frame.setVisible(true);
    }

    @Override
    public void closeScreen() {
        this.frame.setVisible(false);
    }

    public JTable createTable(List<IncidentData> incidentDataList) {
        String[] columnNames = {"Identificador", "Data", "Hora", "Estado", "Cidade", "Bairro", "Rua", "Tipo de Incidente"};
        Object[][] data = incidentDataList.stream().map(IncidentData::convertToObjectArray).toArray(Object[][]::new);
        JTable table = new JTable(data, columnNames);
        table.setFillsViewportHeight(true);
        return table;
    }
}
