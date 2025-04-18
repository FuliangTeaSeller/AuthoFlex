package com.fuliang.authoflex.annotation.handler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public interface AfAnnotationProcessHandler {
    Class<? extends Annotation> getAnnotation();
    default void process() {
    }
}
