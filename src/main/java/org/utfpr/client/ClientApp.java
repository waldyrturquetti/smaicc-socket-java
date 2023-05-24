package org.utfpr.client;

import org.utfpr.client.gui.MenuLogged;
import org.utfpr.client.gui.MenuNonLogged;
import org.utfpr.client.gui.StartClient;

public class ClientApp {

    public static MenuNonLogged menuNonLogged = new MenuNonLogged();

    public static MenuLogged menuLogged = new MenuLogged();

    public static void main(String[] args) {
        new StartClient().buildScreen();
    }
}
