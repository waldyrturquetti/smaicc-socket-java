package org.utfpr.client;

import org.utfpr.client.gui.menu.MenuLogged;
import org.utfpr.client.gui.menu.MenuNonLogged;
import org.utfpr.client.gui.menu.StartClient;

public class ClientApp {

    public static MenuNonLogged menuNonLogged = new MenuNonLogged();

    public static MenuLogged menuLogged = new MenuLogged();

    public static void main(String[] args) {
        new StartClient().buildScreen();
    }
}
