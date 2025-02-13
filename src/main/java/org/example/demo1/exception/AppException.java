package org.example.demo1.exception;

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
