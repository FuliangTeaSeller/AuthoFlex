package com.fuliang.authoflex;

import com.fuliang.authoflex.config.AuthoFlexConfig;
import com.fuliang.authoflex.storage.AfDao;
import com.fuliang.authoflex.storage.AfDaoDefultImpl;
import com.fuliang.authoflex.storage.TimedCacheDao;

public class AfManager {

    private static volatile AfDao afDao;
    public static AfDao getAfDao() {
        if (afDao == null) {
            synchronized (AfManager.class) {
                if (afDao == null) {
                    afDao = new TimedCacheDao();
                }
            }
        }
        return afDao;
    }
    public static void setAfDao(AfDao afDao) {
        AfManager.afDao = afDao;
    }

    private static volatile AuthoFlexConfig authoFlexConfig;
    public static AuthoFlexConfig getAuthoFlexConfig() {
        if (authoFlexConfig == null) {
            synchronized (AfManager.class) {
                if (authoFlexConfig == null) {
                    authoFlexConfig = new AuthoFlexConfig();
                }
            }
        }
        return authoFlexConfig;
    }
    public static void setAuthoFlexConfig(AuthoFlexConfig authoFlexConfig) {
        AfManager.authoFlexConfig = authoFlexConfig;
    }

}
