package com.fuliang.authoflex.config;

import lombok.Data;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
@Data
public class AuthoFlexConfig implements Serializable {
    private static final long serialVersionUID = -6541180061782004705L;
    private String helloMsg = "hello";
    private String tokenPrefix = "token";
    private Long tokenTTLSeconds = TimeUnit.MINUTES.toSeconds(5);
    private String daoType = "default";


}
