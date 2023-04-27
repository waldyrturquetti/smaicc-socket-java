package org.utfpr.server.auth;

import org.utfpr.server.exception.UnauthorizedException;

import java.util.HashMap;
import java.util.Random;

public class Section {

    private static final HashMap<Integer, String> tokens = new HashMap<>();

    public static void addToken(Integer id, String token) {
        tokens.put(id, token);
    }

    public static void removeToken(Integer id) {
        tokens.remove(id);
    }

    public static String createToken() {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder token = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            token.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return token.toString();
    }

    public static void verifyToken(Integer id, String token) throws UnauthorizedException {
        String userToken = tokens.get(id);
        if (userToken.isBlank() || !userToken.equals(token)) {
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
