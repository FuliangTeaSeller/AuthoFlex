package com.fuliang.authoflex.context;

/**
 * web上下文的获取应该与具体实现无关，所以抽象出了 AfContext 这个接口。实现类在 authoflex-spring-boot-starter
 */
public interface AfContext {
    AfRequest getRequest();
    AfResponse getResponse();
}
