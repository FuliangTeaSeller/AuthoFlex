package com.fuliang.authoflex.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface AfCheckPermission {
    String[] value() default {};
    AfCheckMode mode() default AfCheckMode.AND;
}
