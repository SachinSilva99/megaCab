package org.example.demo1.exception;

import org.example.demo1.annotations.ExceptionHandler;
import org.example.demo1.annotations.RestControllerAdvice;
import org.example.demo1.util.ResponseDTO;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Author : SachinSilva
 */
public class ExceptionHandlerRegistry {
    private static final Map<Class<? extends Throwable>, Method> handlers = new HashMap<>();
    private static Object globalExceptionHandlerInstance;

    public static void registerExceptionHandler(Object handler) {
        if (handler.getClass().isAnnotationPresent(RestControllerAdvice.class)) {
            globalExceptionHandlerInstance = handler;

            for (Method method : handler.getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(ExceptionHandler.class)) {
                    ExceptionHandler annotation = method.getAnnotation(ExceptionHandler.class);
                    for (Class<? extends Throwable> exceptionType : annotation.value()) {
                        handlers.put(exceptionType, method);
                    }
                }
            }
        }
    }

    public static ResponseDTO<Object> handleException(Throwable ex) {
        Class<?> exceptionClass = ex.getClass();

        // Try to find the exact match or closest superclass handler
        while (exceptionClass != null) {
            Method handlerMethod = handlers.get(exceptionClass);
            if (handlerMethod != null && globalExceptionHandlerInstance != null) {
                try {
                    return (ResponseDTO<Object>) handlerMethod.invoke(globalExceptionHandlerInstance, ex);
                } catch (Exception e) {
                    return ResponseDTO.error("Error executing exception handler: " + e.getMessage());
                }
            }
            exceptionClass = exceptionClass.getSuperclass(); // Move to the parent exception type
        }

        return ResponseDTO.error("Unhandled exception: " + ex.getMessage());
    }

}
