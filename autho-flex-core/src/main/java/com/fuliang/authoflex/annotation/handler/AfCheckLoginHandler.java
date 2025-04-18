package com.fuliang.authoflex.annotation.handler;

import com.fuliang.authoflex.AfUtil;
import com.fuliang.authoflex.annotation.AfCheckLogin;

import java.lang.annotation.Annotation;
import java.util.Collections;

public class AfCheckLoginHandler implements AfAnnotationProcessHandler{
    @Override
    public Class<? extends Annotation> getAnnotation() {
        return AfCheckLogin.class;
    }

    @Override
    public void process() {
        AfUtil.checkLogin();
    }
}
