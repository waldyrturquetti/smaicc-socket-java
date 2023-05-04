package org.utfpr.server.util;

import org.utfpr.server.exception.UnprocessableAttributeException;

import java.util.HashMap;

public class Check {

    public static void checkName(String name) {
        if (name.length() < 3 || name.length() > 50) {
            throw new UnprocessableAttributeException("Formato do nome é invalido.");
        }
    }

    public static void checkEmail(String email) {
        email = email.trim();

        if (!email.matches("([a-zA-Z0-9]*[~_$&+,:;=?#|'<>.^*()%!-]*){3,50}@([a-zA-Z0-9]*[~_$&+,:;=?#|'<>.^*()%!-]*){3,10}")) {
            throw new UnprocessableAttributeException("Formato do email invalido.");
        }
    }

    public static void checkPassword(String password) {
        if (password.length() < 5 || password.length() > 10 || !password.matches("[a-zA-Z0-9]*")) {
            throw new UnprocessableAttributeException("Formato da senha é invalido.");
        }
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

        if (!token.matches("[a-zA-Z0-9]{20}")) {
            throw new UnprocessableAttributeException("Token invalido.");
        }

        if (!(json.get("id") instanceof Integer)) {
            throw new UnprocessableAttributeException("Id nao processavel");
        }
    }
}
