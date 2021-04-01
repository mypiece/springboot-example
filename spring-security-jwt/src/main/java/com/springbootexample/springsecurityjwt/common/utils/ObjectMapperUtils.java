package com.springbootexample.springsecurityjwt.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.io.InputStream;

public class ObjectMapperUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static String serialization(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    public static <T> T deserialization(String jsonText, Class<T> valueType) throws IOException {
        return (T) mapper.readValue(jsonText, valueType);
    }

    public static <T> T deserialization(InputStream is, Class<T> valueType) throws IOException {
        return (T) mapper.readValue(is, valueType);
    }

}
