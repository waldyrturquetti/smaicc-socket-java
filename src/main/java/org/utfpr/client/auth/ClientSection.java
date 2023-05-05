package org.utfpr.client.auth;

public class ClientSection {

    private static Integer id;
    private static String name;

    private static String token;

    public static Integer getId() {
        return id;
    }

    public static void setId(Integer id) {
        ClientSection.id = id;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        ClientSection.name = name;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        ClientSection.token = token;
    }
}
