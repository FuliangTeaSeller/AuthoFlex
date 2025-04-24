package com.fuliang.authoflex.annotation.handler;

import com.fuliang.authoflex.AfUtil;
import com.fuliang.authoflex.annotation.AfCheckLogin;

import java.lang.annotation.Annotation;

public class AfCheckLoginHandler implements AfAnnotationProcessHandler<AfCheckLogin>{

    @Override
    public void process(AfCheckLogin annotation) {
        AfUtil.checkLogin();
    }
}
