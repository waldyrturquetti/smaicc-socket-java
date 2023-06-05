package org.utfpr.server.gui;

import org.utfpr.common.gui.Gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

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
        this.frame.setSize(1200, 600);
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
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        TableColumnModel tableColumnModel = jTable.getColumnModel();
        tableColumnModel.getColumn(0).setPreferredWidth(50);
        tableColumnModel.getColumn(0).setMaxWidth(80);
        tableColumnModel.getColumn(1).setPreferredWidth(30);
        tableColumnModel.getColumn(1).setMaxWidth(60);
        tableColumnModel.getColumn(2).setPreferredWidth(200);
        tableColumnModel.getColumn(2).setMaxWidth(450);
        tableColumnModel.getColumn(3).setPreferredWidth(200);
        tableColumnModel.getColumn(3).setMaxWidth(600);

        jTable.setRowHeight(20);

        return jTable;
    }

    public void setRowInTable(String hostIP, Integer port, String requestJson, String responseJson) {
        DefaultTableModel defaultTableModel = (DefaultTableModel) this.table.getModel();
        defaultTableModel.addRow(new Object[]{hostIP, port, requestJson, responseJson});
    }
}
