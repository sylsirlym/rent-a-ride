package com.skills.rentaride.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by sylvester
 * Project rent-a-ride
 * User: sylvester
 * Date: 2/24/20
 * Time: 11:28 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateItemDTO {
    private int lendItemType;
    private String serialNumber;
    private int lendItemOwnerProfileID;
}
