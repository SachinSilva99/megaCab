package org.example.demo1.repository.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Author : SachinSilva
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ColumnName {
    String value();
}
