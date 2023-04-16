package org.utfpr.server.util;

import java.util.HashMap;

public class Gateway {

    public static void chooseOperation(HashMap<String, Object> json) {
        System.out.println(json);

         Integer operation = (Integer) json.get("operacao");

        switch (operation) {
            case 1 -> {}
            case 2 -> {}
            case 3 -> {}
            case 4 -> {}
        }
    }
}
