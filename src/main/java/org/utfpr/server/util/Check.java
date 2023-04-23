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

    public static void checkToken(String token) {

    }

    public static void checkIdAndTokenFromJson(HashMap<String, Object> json) {
        String token = (String) json.get("token");

        if (token.isBlank()) {
            throw new UnprocessableAttributeException("Token não processavel");
        }

        checkToken(token);

        try {
             Integer.parseInt((String) json.get("id"));
        } catch (Exception e) {
            throw new UnprocessableAttributeException("Id nao processavel");
        }
    }
}
