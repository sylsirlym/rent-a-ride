package com.skills.rentaride.controller;

import com.skills.rentaride.dtos.requests.LendRequestDTO;
import com.skills.rentaride.dtos.responses.ResponseDTO;
import com.skills.rentaride.services.LendingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sylvester
 * Project rent-a-ride
 * User: sylvester
 * Date: 3/11/20
 * Time: 1:17 AM
 */
@RestController
@RequestMapping("/v1")
@AllArgsConstructor
public class LendingController {
    private LendingService lendingService;
    @PostMapping("/rent-item/request")
    public ResponseDTO lendItem(@RequestBody LendRequestDTO lendRequestDTO){
        return lendingService.requestItem(lendRequestDTO);
    }
}
