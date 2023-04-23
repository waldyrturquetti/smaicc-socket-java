package org.utfpr.server.auth;

import org.utfpr.server.exception.UnauthorizedException;

import java.util.HashMap;

public class Section {

    private static final HashMap<Integer, String> tokens = new HashMap<>();

    public static void addToken(Integer id, String token) {
        tokens.put(id, token);
    }

    public static void removeToken(Integer id) {
        tokens.remove(id);
    }

    public static String createToken() {
        return null;
    }

    public static void verifyToken(Integer id, String token) throws UnauthorizedException {
        String userSection = tokens.get(id);
        if (userSection == null || !userSection.equals(token)) {
            throw new UnauthorizedException();
        }
    }

    public static String authenticatingUser(Integer id) {
        String token = createToken();
        removeToken(id);
        addToken(id, token);
        return token;
    }
}
