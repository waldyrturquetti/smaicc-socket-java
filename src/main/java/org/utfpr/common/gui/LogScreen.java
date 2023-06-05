package org.utfpr.common.gui;

import org.utfpr.common.gui.interfaces.Gui;

import javax.swing.*;

public abstract class LogScreen implements Gui {
    private final JFrame frame;
    protected final JTable table;
    public final static String SERVER_TYPE = "Servidor";
    public final static String CLIENT_TYPE = "Cliente";

    public LogScreen(String hostIp, Integer port, String type) {
        this.frame = new JFrame("Log do " + type + " - Host/IP: " + hostIp + " Porta: " + port);
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


    protected abstract JTable createTable();

    public abstract void setRowInTable(String hostIP, Integer port, String requestJson, String responseJson);
}
