package com.fuliang.authoflex.storage;

public interface AfDao {
    Object get(String key);
    Object put(String key, Object value);
    boolean remove(String key);
    boolean containsKey(String key);
    Object getInternal();
}
