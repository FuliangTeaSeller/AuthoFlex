package com.fuliang.authoflex.interceptor;

import com.fuliang.authoflex.AfManager;
import com.fuliang.authoflex.config.AuthoFlexConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Slf4j
public class AfInterceptor implements HandlerInterceptor {
    private AuthoFlexConfig authoFlexConfig;

    public AfInterceptor() {
        this.authoFlexConfig = AfManager.getAuthoFlexConfig();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //只要配置不开启拦截器，即使存在注解，添加了此拦截器，功能也不生效
        if (!authoFlexConfig.getEnableInterceptor()) return true;

        try {
            if (handler instanceof HandlerMethod) {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                Method method = handlerMethod.getMethod();
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
