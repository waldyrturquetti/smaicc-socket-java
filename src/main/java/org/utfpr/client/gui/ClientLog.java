package org.utfpr.client.gui;

import org.utfpr.common.gui.LogScreen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class ClientLog extends LogScreen {
    public ClientLog(String hostIp, Integer port, String type) {
        super(hostIp, port, type);
    }

    @Override
    protected JTable createTable() {
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("HOST/IP");
        defaultTableModel.addColumn("PORTA");
        defaultTableModel.addColumn("JSON ENVIADO");
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

    @Override
    public void setRowInTable(String hostIP, Integer port, String sendJson, String responseJson) {
        DefaultTableModel defaultTableModel = (DefaultTableModel) super.table.getModel();
        defaultTableModel.addRow(new Object[]{hostIP, port, sendJson, responseJson});
    }
}
