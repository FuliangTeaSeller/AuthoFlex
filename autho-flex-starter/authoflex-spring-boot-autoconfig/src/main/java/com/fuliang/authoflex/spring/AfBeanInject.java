package com.fuliang.authoflex.spring;

import com.fuliang.authoflex.AfManager;
import com.fuliang.authoflex.config.AuthoFlexConfig;
import com.fuliang.authoflex.context.AfContext;
import com.fuliang.authoflex.storage.AfDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

public class AfBeanInject {
    public AfBeanInject(@Autowired(required = false) AuthoFlexConfig authoFlexConfig) {
        if (authoFlexConfig != null) {
            AfManager.setAuthoFlexConfig(authoFlexConfig);
        }
    }
    @Autowired
    public void setAfDao(AfDao afDao) {
        AfManager.setAfDao(afDao);
    }
    @Autowired
    public void setAfContext(AfContext afContext) {
        AfManager.setAfContext(afContext);
    }
}
