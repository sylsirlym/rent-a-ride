package com.skills.rentaride.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by lilian
 * Project rent-a-ride
 * User: lilian
 * Date: 2/25/20
 * Time: 1:11 AM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    private int statusCode;
    private String statusMessage;
    private List<?> data;
}
