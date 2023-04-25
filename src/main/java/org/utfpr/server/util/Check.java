package org.utfpr.server.util;

import org.utfpr.server.exception.UnprocessableAttributeException;

import java.util.HashMap;

public class Check {

    public static void checkName() {

    }

    public static void checkEmail() {

    }

    public static void checkPassword() {

    }

    public static void checkDate() {

    }

    public static void checkHour() {

    }

    public static void checkState() {

    }

    public static void checkCity() {

    }

    public static void checkStreet() {

    }

    public static void checkIdAndTokenFromJson(HashMap<String, Object> json) {
        String token = (String) json.get("token");

        if (token.isBlank()) {
            throw new UnprocessableAttributeException("Token não processavel.");
        }

        if (token.length() != 20 || !token.matches("[a-zA-Z0-9]*")) {
            throw new UnprocessableAttributeException("Token invalido.");
        }

        if (!(json.get("id") instanceof Integer)) {
            throw new UnprocessableAttributeException("Id nao processavel");
        }
    }
}
