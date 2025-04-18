package com.fuliang.authoflex.context;
/**
 * web响应的获取应该与具体实现无关。实现类在 authoflex-servlet
 */
public interface AfResponse {
    void setHeader(String name, String value);
}
