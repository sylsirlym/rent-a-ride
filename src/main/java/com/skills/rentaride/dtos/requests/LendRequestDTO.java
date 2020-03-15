package com.skills.rentaride.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by lilian
 * Project rent-a-ride
 * User: lilian
 * Date: 2/24/20
 * Time: 11:28 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LendRequestDTO {
    private int profileID;
    private int lendItemID;
}
