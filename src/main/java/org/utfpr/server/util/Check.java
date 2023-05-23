package org.utfpr.server.util;

import org.utfpr.common.util.Hash;
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

        /**
         *  In the project specifications it was defined that the email would be as follows:
         *  Email: minimum 3 characters before and after the @.
         *  Required: E-mail will have a maximum length of 50 characters before @ and 10 after (domain).
         */
        if (!email.matches("([a-zA-Z0-9]*[~_$&+,:;=?#|'<>.^*()%!-]*){3,50}@([a-zA-Z0-9]*[~_$&+,:;=?#|'<>.^*()%!-]*){3,10}")) {
            throw new UnprocessableAttributeException("Formato do email invalido.");
        }
    }

    public static void checkPassword(String password) {
        password = Hash.decryption(password);
        if (password.length() < 5 || password.length() > 10 || !password.matches("[a-zA-Z0-9]*")) {
            throw new UnprocessableAttributeException("Formato da senha é invalido.");
        }
    }

    public static void checkDate(String date) {

    }

    public static void checkHour(String hour) {

    }

    public static void checkState(String state) {

    }

    public static void checkCity(String city) {

    }

    public static void checkStreet(String street) {

    }

    public static void checkIncidentType(Integer incidentTypeValue) {

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
