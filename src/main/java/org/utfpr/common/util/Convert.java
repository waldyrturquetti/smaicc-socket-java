package org.utfpr.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.utfpr.common.dto.Data;
import org.utfpr.server.exception.BadJsonException;

import java.util.HashMap;
import java.util.Map;

public class Convert {

    public static Data convertHashMapToData(HashMap<String, Object> json, Class<? extends Data> dataClass) {
        return new ObjectMapper().convertValue(json, dataClass);
    }

    public static HashMap<String, Object> convertDataToHashMap(Data data) {
        return (HashMap<String, Object>) new ObjectMapper().convertValue(data, Map.class);
    }

    public static String convertHashMapToString(HashMap<String, Object> json) {
        try {
            return new ObjectMapper().writeValueAsString(json);
        } catch (JsonProcessingException e) {
            throw new BadJsonException(e.getMessage());
        }
    }

    public static HashMap<String, Object> convertStringToHashMap(String message) {
        try {
            return new ObjectMapper().readValue(message, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new BadJsonException(e.getMessage());
        }
    }
}
