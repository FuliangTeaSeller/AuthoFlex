package com.fuliang.authoflex.config;

import java.io.Serializable;

public class AuthoFlexConfig implements Serializable {
    private static final long serialVersionUID = -6541180061782004705L;
    public String helloMsg = "hello";
    public String tokenPrefix = "token";

    public String getHelloMsg() {
        return helloMsg;
    }

    public void setHelloMsg(String helloMsg) {
        this.helloMsg = helloMsg;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }
}
