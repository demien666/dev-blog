package com.demien.spring.lifecycle.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface GeneratedName {
    int minLength();
    int maxLength();
}
