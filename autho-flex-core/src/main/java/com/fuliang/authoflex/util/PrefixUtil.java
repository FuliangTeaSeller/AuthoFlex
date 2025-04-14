package com.fuliang.authoflex.util;

import com.fuliang.authoflex.AfManager;
import com.fuliang.authoflex.config.AuthoFlexConfig;

public class PrefixUtil {

    public static String ID;
    public static String TOKEN;

    static {
        AuthoFlexConfig config = AfManager.getAuthoFlexConfig();
        ID = config.getIdPrefix() + ":";
        TOKEN = config.getTokenPrefix() + ":";
    }

    public static String addIdPrefix(String key) {
        return ID + key;
    }

    public static String addTokenPrefix(String key) {
        return TOKEN + key;
    }
}
