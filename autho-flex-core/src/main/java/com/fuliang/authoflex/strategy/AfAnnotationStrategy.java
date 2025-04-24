package com.fuliang.authoflex.strategy;

import com.fuliang.authoflex.annotation.AfCheckLogin;
import com.fuliang.authoflex.annotation.AfCheckPermission;
import com.fuliang.authoflex.annotation.AfCheckRole;
import com.fuliang.authoflex.annotation.handler.AfAnnotationProcessHandler;
import com.fuliang.authoflex.annotation.handler.AfCheckLoginHandler;
import com.fuliang.authoflex.annotation.handler.AfCheckPermissionHandler;
import com.fuliang.authoflex.annotation.handler.AfCheckRoleHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 策略类，单例，里面是一些预先写好的方法。你可以通过替换这些方法实现你需要的功能
 */
public class AfAnnotationStrategy {
    public static final AfAnnotationStrategy instance=new AfAnnotationStrategy();

    Map<Class<? extends Annotation>, AfAnnotationProcessHandler> annotationHandlerMap=new ConcurrentHashMap<>();

    public AfAnnotationStrategy() {
        init();
    }

    public void init(){
        annotationHandlerMap.put(AfCheckLogin.class, new AfCheckLoginHandler());
        annotationHandlerMap.put(AfCheckRole.class, new AfCheckRoleHandler());
        annotationHandlerMap.put(AfCheckPermission.class, new AfCheckPermissionHandler());
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
            Annotation classAnnotation = declaringClass.getAnnotation(annotationClass);
            if(classAnnotation!=null){
                handler.process(classAnnotation);
                continue;
            }

            //方法上存在对应注解，执行handler
            Annotation methodAnnotation = method.getAnnotation(annotationClass);
            if(methodAnnotation!=null){
                handler.process(methodAnnotation);
            }
        }
    }
}
