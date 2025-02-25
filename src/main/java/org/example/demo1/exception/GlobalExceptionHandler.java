package org.example.demo1.exception;

/**
 * Author : SachinSilva
 */

import lombok.extern.slf4j.Slf4j;
import org.example.demo1.annotations.ExceptionHandler;
import org.example.demo1.annotations.RestControllerAdvice;
import org.example.demo1.util.ResponseDTO;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseDTO<Object> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseDTO.error("Invalid request: " + ex.getMessage());
    }

    @ExceptionHandler({Exception.class})
    public ResponseDTO<Object> handleGenericException(Exception ex) {
        System.out.println(ex.getMessage()+ ex);
        return ResponseDTO.error("An unexpected error occurred: " + ex.getMessage());
    }
    @ExceptionHandler({AppException.class})
    public ResponseDTO<Object> handleAppException(Exception ex) {

        return ResponseDTO.error("An unexpected error occurred: " + ex.getMessage());
    }
}

