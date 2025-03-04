package com.sachin.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Author : SachinSilva
 */
public interface GlobalObjectMapper {
    ObjectMapper objectMapper = new ObjectMapper();
    static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }
}