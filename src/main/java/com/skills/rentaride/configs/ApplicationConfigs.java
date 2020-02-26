package com.skills.rentaride.configs;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by sylvester
 * Project rent-a-ride
 * User: sylvester
 * Date: 2/25/20
 * Time: 1:13 AM
 */
@Configuration
@ConfigurationProperties("rent-a-ride.service")
@Data
@NoArgsConstructor
public class ApplicationConfigs {
    private int successStatusCode;
    private String defaultSuccessMessage;
    private String defaultFailureMessage;
    //Pin
    private int pinLength;
    private String otpPinStatus;
    private String pinHashAlgorithm;
    @Value("#{${profile.pin-status-codes-map}}")
    private Map<Integer, Integer> pinStatusCodeMap;
}
