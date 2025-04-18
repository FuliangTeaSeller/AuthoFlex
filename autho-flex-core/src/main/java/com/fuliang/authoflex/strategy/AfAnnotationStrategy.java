package com.fuliang.authoflex.strategy;

import com.fuliang.authoflex.annotation.AfCheckLogin;
import com.fuliang.authoflex.annotation.handler.AfAnnotationProcessHandler;
import com.fuliang.authoflex.annotation.handler.AfCheckLoginHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AfAnnotationStrategy {
    public static final AfAnnotationStrategy instance=new AfAnnotationStrategy();

    Map<Class<? extends Annotation>, AfAnnotationProcessHandler> annotationHandlerMap=new ConcurrentHashMap<>();

    public AfAnnotationStrategy() {
        init();
    }

    public void init(){
        annotationHandlerMap.put(AfCheckLogin.class, new AfCheckLoginHandler());
    }

    /**
     * 处理方法上的注解进行校验，如果不通过抛出异常
     * @param method
     */
    public void processMethod(Method method){
        Class<?> declaringClass = method.getDeclaringClass();
        for (Map.Entry<Class<? extends Annotation>, AfAnnotationProcessHandler> entry : annotationHandlerMap.entrySet()) {
            Class<? extends Annotation> annotationClass = entry.getKey();
            AfAnnotationProcessHandler handler = entry.getValue();

            //类上存在对应注解，执行handler
            if(declaringClass.getAnnotation(annotationClass)!=null){
                handler.process();
                continue;
            }

            //方法上存在对应注解，执行handler
            if(method.getAnnotation(annotationClass)!=null){
                handler.process();
            }
        }
    }
}
