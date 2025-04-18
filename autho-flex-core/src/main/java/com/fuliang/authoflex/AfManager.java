package com.fuliang.authoflex;

import com.fuliang.authoflex.config.AuthoFlexConfig;
import com.fuliang.authoflex.context.AfContext;
import com.fuliang.authoflex.storage.AfDao;
import com.fuliang.authoflex.storage.AfDaoDefultImpl;

/**
 * 框架的所有组件都会自动挂载到AfManager，你可以通过AfManager获取这些组件
 */
public class AfManager {

    private static volatile AfDao afDao;
    public static AfDao getAfDao() {
        if (afDao == null) {
            synchronized (AfManager.class) {
                if (afDao == null) {
                    afDao = new AfDaoDefultImpl();
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

    private static volatile AfContext afContext;
    public static AfContext getAfContext() {
        return afContext;
    }
    public static void setAfContext(AfContext afContext) {
        AfManager.afContext = afContext;
    }

}
