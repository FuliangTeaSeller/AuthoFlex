package com.fuliang.authoflex.util;

import com.fuliang.authoflex.AfManager;
import com.fuliang.authoflex.config.AuthoFlexConfig;

public class PrefixUtil {

    public static String ID;
    public static String TOKEN;
    public static String COMMON_PREFIX;

    static {
        AuthoFlexConfig config = AfManager.getAuthoFlexConfig();
        ID = config.getIdPrefix() + ":";
        TOKEN = config.getTokenPrefix() + ":";
        COMMON_PREFIX = config.getName() + ":";
    }

    public static String addIdPrefix(String key) {
        return COMMON_PREFIX + ID + key;
    }

    public static String addTokenPrefix(String key) {
        return COMMON_PREFIX + TOKEN + key;
    }
}
