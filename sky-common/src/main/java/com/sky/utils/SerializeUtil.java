package com.sky.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sky.json.JacksonObjectMapper;

public class SerializeUtil {
    private static final JacksonObjectMapper mapper = new JacksonObjectMapper();

    public static String serialize(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T deserialize(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
