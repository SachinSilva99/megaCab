package org.example.demo1.annotations;
import java.lang.annotation.*;

/**
 * Author : SachinSilva
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RestControllerAdvice {
}