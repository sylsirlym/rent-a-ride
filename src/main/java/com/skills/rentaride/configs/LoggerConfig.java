package com.skills.rentaride.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by lilian
 * Project rent-a-ride
 * User: lilian
 * Date: 2/25/20
 * Time: 1:14 AM
 */
@Configuration
public class LoggerConfig {
    /**
     * Produce logger logger.
     *
     * @return the logger
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Logger produceLogger() {
        return LoggerFactory.getLogger(this.getClass());
    }
}
