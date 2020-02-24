package com.skills.rentaride.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skills.rentaride.dtos.requests.CreateUserDTO;
import com.skills.rentaride.dtos.responses.ResponseDTO;
import com.skills.rentaride.entites.CustomersEntity;
import com.skills.rentaride.entites.ProfilesEntity;
import com.skills.rentaride.repository.CustomersRepository;
import com.skills.rentaride.repository.ProfilesRepository;
import com.skills.rentaride.utilities.Utils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sylvester
 * Project rent-a-ride
 * User: sylvester
 * Date: 2/25/20
 * Time: 1:07 AM
 */
@Service
@AllArgsConstructor
public class RentARideService {
    private ProfilesRepository profilesRepository;
    private CustomersRepository customersRepository;
    private Utils utils;
    private ModelMapper modelMapper;
    private final Logger logger;
    private final ObjectMapper objectMapper;

    public ResponseDTO createUser(CreateUserDTO createUserDTO) throws JsonProcessingException {
        CustomersEntity customersEntity = utils.createCustomerObject(createUserDTO);
        customersRepository.save(customersEntity);
        ProfilesEntity profilesEntity = utils.createProfileObject(createUserDTO, customersEntity);
        logger.info("Profiles Entity to save::{}", objectMapper.writeValueAsString(profilesEntity));
        ProfilesEntity newP = profilesRepository.save(profilesEntity);
        return new ResponseDTO(
                200,
                "Successfully registered",
                List.of(newP)
        );
    }
}
