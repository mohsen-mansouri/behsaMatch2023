package com.example.demodemo.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.nio.charset.Charset;
import java.util.Map;


public class JsonCodec {
    public static final ObjectMapper mapper = new ObjectMapper();

    public JsonCodec() {
    }

    public static <T> T toObject(byte[] bytes, Class<T> objClass) {
        try {
            return mapper.readValue(new String(bytes, Charset.forName("utf-8")), objClass);
        } catch (Exception var3) {
            throw new RuntimeException(var3);
        }
    }

    public static <T> T toObject(String json, Class<T> objClass) {
        try {
            return mapper.readValue(json, objClass);
        } catch (Exception var3) {
            throw new RuntimeException(var3);
        }
    }

    public static <T> T toObject(Map map, Class<T> clazz) {
        return mapper.convertValue(map, clazz);
    }

    public static <T> T toObject(Object object, Class<T> clazz) {
        return mapper.convertValue(object, clazz);
    }

    public static Map toMap(Object o) {
        return (Map)mapper.convertValue(o, Map.class);
    }

    public static byte[] toBytes(Object obj) {
        try {
            return toString(obj).getBytes(Charset.forName("utf-8"));
        } catch (Exception var2) {
            throw new RuntimeException(var2);
        }
    }

    public static String toString(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception var2) {
            throw new RuntimeException(var2);
        }
    }

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
    }
}
