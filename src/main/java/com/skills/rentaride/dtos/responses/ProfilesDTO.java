package com.skills.rentaride.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by lilian
 * Project rent-a-ride
 * User: lilian
 * Date: 2/25/20
 * Time: 11:04 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfilesDTO {
    private int profileID;
    private int pinStatus;
    private String msisdn;
    private String fName;
    private String otherNames;
    private String emailAddress;
}
