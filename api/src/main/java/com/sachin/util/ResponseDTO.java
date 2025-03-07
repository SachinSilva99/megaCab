package com.sachin.util;

/**
 * Author : SachinSilva
 */
public record ResponseDTO<T>(String status, String message, T content) {
    public static ResponseDTO<Object> success() {
        return new ResponseDTO<>("00", "Success!", null);
    }

    public static <T> ResponseDTO<T> success(T content) {
        return new ResponseDTO<>("00", "Success!", content);
    }

    public static <T> ResponseDTO<T> customResponse(String status, String message, T content) {
        return new ResponseDTO<>(status, message, content);
    }

    public static <T> ResponseDTO<T> customResponse(String status, String message) {
        return new ResponseDTO<>(status, message, null);
    }


    public static ResponseDTO<Object> error(String message) {
        return new ResponseDTO<>("99", message, null);
    }
}