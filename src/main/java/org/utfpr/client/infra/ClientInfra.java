package org.utfpr.client.infra;

import org.utfpr.common.gui.LogScreen;

public class ClientInfra {

    private static String requestJson;
    private static String responseJson;
    private static LogScreen clientLog;

    public static String getRequestJson() {
        return requestJson;
    }

    public static void setRequestJson(String requestJson) {
        ClientInfra.requestJson = requestJson;
    }

    public static String getResponseJson() {
        return responseJson;
    }

    public static void setResponseJson(String responseJson) {
        ClientInfra.responseJson = responseJson;
    }

    public static LogScreen getClientLog() {
        return clientLog;
    }

    public static void setClientLog(LogScreen clientLog) {
        ClientInfra.clientLog = clientLog;
    }
}
