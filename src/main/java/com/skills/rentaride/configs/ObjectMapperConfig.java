package com.skills.rentaride.configs;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by sylvester
 * Project rent-a-ride
 * User: sylvester
 * Date: 2/25/20
 * Time: 1:17 AM
 */
@Configuration
public class ObjectMapperConfig {
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        objectMapper.setDateFormat(df);
        return objectMapper;
    }
}
