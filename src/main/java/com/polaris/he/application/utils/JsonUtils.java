package com.polaris.he.application.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;

/**
 * User: hexie
 * Date: 2019-01-05 22:20
 * Description:
 */
@Slf4j
public class JsonUtils {

    private static final ObjectMapper OBJECT_MAPPER = Jackson2ObjectMapperBuilder.
            json().
            serializationInclusion(JsonInclude.Include.NON_NULL).
            featuresToEnable(MapperFeature.PROPAGATE_TRANSIENT_MARKER).
            build();

    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }

    public static <T> T toJavaObject(JsonNode jsonNode, Class<T> clazz) {
        try {
            return getObjectMapper().treeToValue(jsonNode, clazz);
        } catch (JsonProcessingException e) {
            log.error("convert json error", e);
        }
        return null;
    }

    public static <T> T toJavaObject(String content, Class<T> clazz) {
        try {
            if (content == null) {
                return null;
            }
            return getObjectMapper().readValue(content, clazz);
        } catch (IOException e) {
            log.error("read json string error", e);
        }
        return null;
    }

    public static String toJsonString(Object object) {
        try {
            return getObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("write json string error", e);
        }
        return null;
    }

    public static JsonNode javaObjectToObjectJson(Object object) {
        return toJavaObject(toJsonString(object), JsonNode.class);
    }
}