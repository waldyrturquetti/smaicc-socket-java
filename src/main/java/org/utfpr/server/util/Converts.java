package org.utfpr.server.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.utfpr.server.dto.Data;
import org.utfpr.server.exception.BadJsonException;

import java.util.HashMap;

public class Converts {

    public static Data convertHashMapToData(HashMap<String, Object> json, Data data) {
        return new ObjectMapper().convertValue(json, data.getClass());
    }

    private static HashMap<String, Object> convertDataToHashMap(Data data) {
        return null;
    }

    public static HashMap<String, Object> convertStringToHashMap(String message) {
        try {
            return new ObjectMapper().readValue(message, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new BadJsonException(e.getMessage());
        }
    }
}
