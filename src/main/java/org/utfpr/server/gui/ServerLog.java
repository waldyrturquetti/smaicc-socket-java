package org.utfpr.server.gui;

import org.utfpr.common.gui.Gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ServerLog implements Gui {

    private final JFrame frame;
    private final JTable table;

    public ServerLog(String port) {
        this.frame = new JFrame("Log do Servidor - Porta: " + port);
        this.table = this.createTable();
    }

    @Override
    public void buildScreen() {
        JScrollPane scrollPane = new JScrollPane(this.table);
        this.frame.getContentPane().add(scrollPane);
        this.frame.setSize(700, 600);
        this.frame.setVisible(true);
    }

    @Override
    public void closeScreen() {
        this.frame.setVisible(false);
    }

    private JTable createTable() {
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("HOST/IP");
        defaultTableModel.addColumn("PORTA");
        defaultTableModel.addColumn("JSON RECEBIDO");
        defaultTableModel.addColumn("JSON RETORNADO");

        JTable jTable = new JTable(defaultTableModel);
        jTable.setFillsViewportHeight(true);

        return jTable;
    }

    public void setRowInTable(String hostIP, Integer port, String requestJson, String responseJson) {

    }
}
