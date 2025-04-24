package com.fuliang.authoflex.annotation.handler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public interface  AfAnnotationProcessHandler <T extends Annotation> {
    default void process(T annotation) {
    }
}
