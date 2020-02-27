package com.skills.rentaride.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by sylvester
 * Project rent-a-ride
 * User: sylvester
 * Date: 2/27/20
 * Time: 7:46 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthPinDTO {
    private String msisdn;
    private String pin;
}
