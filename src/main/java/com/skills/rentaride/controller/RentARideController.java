package com.skills.rentaride.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skills.rentaride.dtos.requests.CreateUserDTO;
import com.skills.rentaride.dtos.responses.ResponseDTO;
import com.skills.rentaride.services.RentARideService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sylvester
 * Project rent-a-ride
 * User: sylvester
 * Date: 2/25/20
 * Time: 1:06 AM
 */
@RestController
@RequestMapping("/v1")
@AllArgsConstructor
public class RentARideController {
 private RentARideService rentARideService;
 private final Logger logger;
 private final ObjectMapper objectMapper;

 @PostMapping("/customer/create")
    public ResponseDTO createUser(@RequestBody CreateUserDTO createUserDTO) throws JsonProcessingException {
     logger.info("Create user controller. Payload::{}", objectMapper.writeValueAsString(createUserDTO));
     return rentARideService.createUser(createUserDTO);
 }
}
