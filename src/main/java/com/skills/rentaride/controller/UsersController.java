package com.skills.rentaride.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skills.rentaride.dtos.requests.AuthPinDTO;
import com.skills.rentaride.dtos.requests.CreateUserDTO;
import com.skills.rentaride.dtos.responses.ResponseDTO;
import com.skills.rentaride.exceptions.GenericException;
import com.skills.rentaride.exceptions.InvalidPinStatusException;
import com.skills.rentaride.exceptions.ProfileNotFoundException;
import com.skills.rentaride.services.UsersService;
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
    private final Logger logger;
    private final ObjectMapper objectMapper;
    private UsersService usersService;

 /**
  * This is an endpoint for creating a user.
  *
  * @param createUserDTO the create user dto
  * @return the response dto
  * @throws JsonProcessingException   the json processing exception
  * @throws InvalidPinStatusException the invalid pin status exception
  * @throws GenericException          the generic exception
  */
 @PostMapping("/customer/create")
    public ResponseDTO createUser(@RequestBody CreateUserDTO createUserDTO) throws JsonProcessingException, InvalidPinStatusException, GenericException {
        logger.info("Create user controller. Payload::{}", objectMapper.writeValueAsString(createUserDTO));
        return usersService.createUser(createUserDTO);
    }

 /**
  * Fetch customer profile response using using mobile number.
  *
  * @param msisdn the msisdn
  * @return the response dto
  * @throws JsonProcessingException  the json processing exception
  * @throws ProfileNotFoundException the profile not found exception
  */
 @GetMapping("/customer/{msisdn}")
    public ResponseDTO fetchCustomerProfile(@PathVariable String msisdn) throws JsonProcessingException, ProfileNotFoundException {
        logger.info("Fetch user controller. Msisdn::{}", msisdn);
        return usersService.fetchUser(msisdn);
    }

 /**
  * Authenticate customer pin response a new member gets an otp but an active member will have his or her own pin.
  *
  * @param authPinDTO the auth pin dto
  * @return the response dto
  * @throws JsonProcessingException  the json processing exception
  * @throws ProfileNotFoundException the profile not found exception
  * @throws GenericException         the generic exception
  */
 @PostMapping("/customer/authenticate")
    public ResponseDTO authenticateCustomerPin(@RequestBody AuthPinDTO authPinDTO) throws JsonProcessingException, ProfileNotFoundException, GenericException {
        logger.info("Auth Customer Pin. Payload::{}", objectMapper.writeValueAsString(authPinDTO));
        return usersService.authPin(authPinDTO);
    }
}
