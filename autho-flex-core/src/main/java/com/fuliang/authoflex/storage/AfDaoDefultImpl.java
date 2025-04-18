package com.fuliang.authoflex.storage;


import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

//@Component
//@ConditionalOnProperty(prefix = "autho-flex", name = "dao-type", havingValue = "default", matchIfMissing = true)
public class AfDaoDefultImpl implements AfDao {
    public Map<String, String> map;

    public AfDaoDefultImpl() {
        this.map = new ConcurrentHashMap<>();
    }

    @Override
    public String get(String key) {
        return map.get(key);
    }

    @Override
    public String put(String key, String value) {
        return map.put(key, value);
    }

    @Override
    public String put(String key, String value, long timeout) {
        return map.put(key, value);
    }

    @Override
    public boolean remove(String key) {
        return map.remove(key) != null;
    }

    @Override
    public boolean containsKey(String key) {
        return map.containsKey(key);
    }

    @Override
    public Object getInternal() {
        return map;
    }
}
