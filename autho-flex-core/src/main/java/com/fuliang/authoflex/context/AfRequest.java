package com.fuliang.authoflex.context;
/**
 * web请求的获取应该与具体实现无关。实现类在 authoflex-servlet
 */
public interface AfRequest {
    String getHeader(String name);
}
