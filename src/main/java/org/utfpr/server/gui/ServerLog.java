package org.utfpr.server.gui;

import org.utfpr.common.gui.LogScreen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class ServerLog extends LogScreen {
    public ServerLog(String hostIp, Integer port, String type) {
        super(hostIp, port, type);
    }

    @Override
    protected JTable createTable(){
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("HOST/IP");
        defaultTableModel.addColumn("PORTA");
        defaultTableModel.addColumn("JSON RECEBIDO");
        defaultTableModel.addColumn("JSON RETORNADO");

        JTable jTable = new JTable(defaultTableModel);
        jTable.setFillsViewportHeight(true);
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        TableColumnModel tableColumnModel = jTable.getColumnModel();
        tableColumnModel.getColumn(0).setPreferredWidth(80);
        tableColumnModel.getColumn(1).setPreferredWidth(60);
        tableColumnModel.getColumn(2).setPreferredWidth(400);
        tableColumnModel.getColumn(3).setPreferredWidth(650);

        jTable.setRowHeight(20);

        return jTable;
    }

    @Override
    public void setRowInTable(String hostIP, Integer port, String requestJson, String responseJson) {
        DefaultTableModel defaultTableModel = (DefaultTableModel) super.table.getModel();
        defaultTableModel.addRow(new Object[]{hostIP, port, requestJson, responseJson});
    }
}
