package com.skills.rentaride.configs;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * Created by lilian
 * Project rent-a-ride
 * User: lilian
 * Date: 2/25/20
 * Time: 1:13 AM
 */
@Configuration
@ConfigurationProperties("rent-a-ride.service")
@Data
@NoArgsConstructor
public class ApplicationConfigs {
    private int successStatusCode;
    private int failureStatusCode;
    private int authFailedStatusCode;
    private int pendingStatus;
    private String defaultSuccessMessage;
    private String defaultFailureMessage;
    //Pin
    private int pinLength;
    private String otpPinStatus;
    private String pinHashAlgorithm;
    @Value("#{${profile.pin-status-codes-map}}")
    private Map<Integer, Integer> pinStatusCodeMap;
}
