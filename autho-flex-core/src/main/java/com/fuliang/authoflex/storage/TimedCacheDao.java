package com.fuliang.authoflex.storage;

import cn.hutool.cache.impl.TimedCache;

import java.util.concurrent.TimeUnit;

public class TimedCacheDao implements AfDao {
    TimedCache<String, Object> cache;

    public TimedCacheDao() {
        this.cache = new TimedCache<>(TimeUnit.MINUTES.toMillis(5));
    }

    @Override
    public Object get(String key) {
        return cache.get(key);
    }

    @Override
    public Object put(String key, Object value) {
        cache.put(key, value);
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
