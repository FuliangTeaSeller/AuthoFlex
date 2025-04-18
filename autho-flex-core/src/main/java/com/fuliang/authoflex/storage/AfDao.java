package com.fuliang.authoflex.storage;

public interface AfDao {
    String get(String key);
    String put(String key, String value);
    String put(String key, String value,long timeout);
    boolean remove(String key);
    boolean containsKey(String key);
    Object getInternal();
}
