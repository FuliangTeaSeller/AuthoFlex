package com.fuliang.authoflex.storage;

/**
 * 解决的是框架的数据存哪里的问题，比如说token->id关系的存储。你可以通过引入不同的模块灵活决定使用哪个实现
 */
public interface AfDao {
    String get(String key);
    String put(String key, String value);
    String put(String key, String value,long timeout);
    boolean remove(String key);
    boolean containsKey(String key);
    Object getInternal();
}
