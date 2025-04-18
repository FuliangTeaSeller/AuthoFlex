package com.fuliang.authoflex.storage;

import cn.hutool.cache.impl.TimedCache;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@ConditionalOnProperty(prefix = "autho-flex",name = "dao-type",havingValue = "timed-cache")
public class TimedCacheDao implements AfDao {
    TimedCache<String, String> cache;

    public TimedCacheDao() {
        this.cache = new TimedCache<>(TimeUnit.MINUTES.toMillis(5));
    }

    @Override
    public String get(String key) {
        return cache.get(key);
    }

    @Override
    public String put(String key, String value) {
        cache.put(key, value);
        return value;
    }

    @Override
    public String put(String key, String value, long timeout) {
        cache.put(key, value, timeout);
        return value;
    }

    @Override
    public boolean remove(String key) {
        cache.remove(key);
        return true;
    }

    @Override
    public boolean containsKey(String key) {
        return cache.containsKey(key);
    }

    @Override
    public Object getInternal() {
        return cache;
    }
}
