package com.sachin.exception;

/**
 * Author : SachinSilva
 */
public class AppException extends RuntimeException{
    public AppException() {
    }

    public AppException(String message) {
        super(message);
    }
}
