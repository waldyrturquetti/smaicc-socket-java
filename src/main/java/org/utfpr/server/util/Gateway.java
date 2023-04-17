package org.utfpr.server.util;

import java.util.HashMap;

public class Gateway {

    public static void chooseOperation(HashMap<String, Object> json) {

        Integer operation = (Integer) json.get("operacao");

        switch (operation) {
            case 1 -> {}
            case 2 -> {
            }
            case 3 -> {}
            case 4 -> {}
            case 5 -> {}
            case 6 -> {}
            case 7 -> {}
            case 8 -> {}
            case 9 -> {}
        }
    }
}
