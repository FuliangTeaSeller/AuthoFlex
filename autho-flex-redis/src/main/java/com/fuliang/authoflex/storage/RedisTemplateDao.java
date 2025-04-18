package com.fuliang.authoflex.storage;

import com.fuliang.authoflex.config.AuthoFlexConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

@ConditionalOnProperty(prefix = "autho-flex",name = "dao-type",havingValue = "redis")
public class RedisTemplateDao implements AfDao {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public String put(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
        return value;
    }

    @Override
    public String put(String key, String value, long timeout) {
        stringRedisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
        return value;
    }

    @Override
    public boolean remove(String key) {
        return Boolean.TRUE.equals(stringRedisTemplate.delete(key));
    }

    @Override
    public boolean containsKey(String key) {
        return stringRedisTemplate.opsForValue().get(key) != null;
    }

    @Override
    public Object getInternal() {
        return stringRedisTemplate;
    }
}
