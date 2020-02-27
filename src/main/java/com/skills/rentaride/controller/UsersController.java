package com.skills.rentaride.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skills.rentaride.dtos.requests.AuthPinDTO;
import com.skills.rentaride.dtos.requests.CreateUserDTO;
import com.skills.rentaride.dtos.responses.ResponseDTO;
import com.skills.rentaride.exceptions.GenericException;
import com.skills.rentaride.exceptions.InvalidPinStatusException;
import com.skills.rentaride.exceptions.ProfileNotFoundException;
import com.skills.rentaride.services.RentARideService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;

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
public class UsersController {
 private RentARideService rentARideService;
 private final Logger logger;
 private final ObjectMapper objectMapper;

 @PostMapping("/customer/create")
    public ResponseDTO createUser(@RequestBody CreateUserDTO createUserDTO) throws JsonProcessingException, InvalidPinStatusException, GenericException {
     logger.info("Create user controller. Payload::{}", objectMapper.writeValueAsString(createUserDTO));
     return rentARideService.createUser(createUserDTO);
 }

 @GetMapping("/customer/{msisdn}")
 public ResponseDTO fetchCustomerProfile(@PathVariable String msisdn) throws JsonProcessingException, ProfileNotFoundException {
  logger.info("Fetch user controller. Msisdn::{}", msisdn);
  return rentARideService.fetchUser(msisdn);
 }

 @PostMapping("/customer/authenticate")
 public ResponseDTO authenticateCustomerPin(@RequestBody AuthPinDTO authPinDTO) throws JsonProcessingException, ProfileNotFoundException, GenericException {
  logger.info("Auth Customer Pin. Payload::{}", objectMapper.writeValueAsString(authPinDTO));
  return rentARideService.authPin(authPinDTO);
 }
}
