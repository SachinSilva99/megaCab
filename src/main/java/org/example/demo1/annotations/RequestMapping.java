package org.example.demo1.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Author : SachinSilva
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    enum HttpMethod {
        GET, POST, PUT, DELETE, PATCH, OPTIONS, HEAD
    }
    String value();
    HttpMethod method() default HttpMethod.GET;
}
