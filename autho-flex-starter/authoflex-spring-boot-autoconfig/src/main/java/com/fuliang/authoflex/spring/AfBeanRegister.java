package com.fuliang.authoflex.spring;

import com.fuliang.authoflex.config.AuthoFlexConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

public class AfBeanRegister {
    @Bean
    @ConfigurationProperties(prefix = "autho-flex")
    public AuthoFlexConfig getAuthoFlexConfig() {
        return new AuthoFlexConfig();
    }
}
