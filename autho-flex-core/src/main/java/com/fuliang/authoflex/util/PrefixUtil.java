package com.fuliang.authoflex.util;

public class PrefixUtil {

    public static final String ID = "id:";
    public static final String TOKEN = "token:";

    public static String addIdPrefix(String key) {
        return ID + key;
    }

    public static String addTokenPrefix(String key) {
        return TOKEN + key;
    }
}
