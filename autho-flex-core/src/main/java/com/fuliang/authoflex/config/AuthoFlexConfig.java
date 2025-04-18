package com.fuliang.authoflex.config;

import lombok.Data;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@Data
public class AuthoFlexConfig implements Serializable {
    private static final long serialVersionUID = -6541180061782004705L;

    /**
     * 在框架正常启动时通过日志打印
     */
    private String helloMsg = "AuthoFlex加载完成";

    /**
     * 本项目的唯一标识，用于持久层key前缀
     */
    private String name = "authoflex";

    /**
     * 持久层token->id 关系的key前缀
     */
    private String tokenPrefix = "token";

    /**
     * 持久层id->token 关系的key前缀
     */
    private String idPrefix = "id";

    /**
     * 持久层token有效期，过期自动清除
     */
    private Long tokenTTLSeconds = TimeUnit.MINUTES.toSeconds(5);

    /**
     * 持久层实现类
     * - default: ConcurrentHashMap实现，无TTL，永久存储
     * - timed-cache: hutool-cache实现，需要引入autho-flex-cache
     * - redis: redis实现，需要引入autho-flex-redis
     */
    private String daoType = "default";

    /**
     * 注解鉴权拦截器启用开关，关闭时即使添加了拦截器也不会执行处理逻辑，直接放行
     */
    private Boolean enableInterceptor = false;

    /**
     * 多次登录时是否覆盖旧token
     */
    private Boolean tokenOverride = true;
}
