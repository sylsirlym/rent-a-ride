package com.skills.rentaride.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skills.rentaride.configs.ApplicationConfigs;
import com.skills.rentaride.dtos.requests.CreateUserDTO;
import com.skills.rentaride.dtos.responses.ProfilesDTO;
import com.skills.rentaride.dtos.responses.ResponseDTO;
import com.skills.rentaride.entites.CustomersEntity;
import com.skills.rentaride.entites.ProfilesEntity;
import com.skills.rentaride.exceptions.GenericException;
import com.skills.rentaride.exceptions.InvalidPinStatusException;
import com.skills.rentaride.exceptions.ProfileNotFoundException;
import com.skills.rentaride.repository.CustomersRepository;
import com.skills.rentaride.repository.ProfilesRepository;
import com.skills.rentaride.utilities.Utils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
    private ApplicationConfigs applicationConfigs;
    private final Logger logger;
    private final ObjectMapper objectMapper;
    private final ModelMapper modelMapper;

    public ResponseDTO createUser(CreateUserDTO createUserDTO) throws JsonProcessingException, InvalidPinStatusException, GenericException {
        CustomersEntity customersEntity = utils.createCustomerObject(createUserDTO);
        customersRepository.save(customersEntity);
        ProfilesEntity profilesEntity = utils.createProfileObject(createUserDTO, customersEntity);
        logger.info("Profiles Entity to save::{}", objectMapper.writeValueAsString(profilesEntity));
        ProfilesEntity newP = profilesRepository.save(profilesEntity);
        //Generate One Time Pin
        utils.generateOTP(newP);
        profilesRepository.save(newP);

        return utils.formulateResponse(
                applicationConfigs.getSuccessStatusCode(),
                applicationConfigs.getDefaultSuccessMessage(),
                utils.mapProfileDetails(profilesEntity),
                new HashMap<>()
        );
    }

    public ResponseDTO fetchUser(String msisdn) throws ProfileNotFoundException, JsonProcessingException {
        ProfilesEntity profilesEntity = profilesRepository.findProfilesEntityByMsisdn(msisdn);
        if(profilesEntity==null){
            throw new ProfileNotFoundException("Profile not found");
        }
        ProfilesDTO profilesDTO = utils.mapProfileDetails(profilesEntity);
        logger.info("Mapped profile::{}", objectMapper.writeValueAsString(profilesDTO));
        return utils.formulateResponse(
                applicationConfigs.getPinStatusCodeMap().get(profilesDTO.getPinStatus()),
                applicationConfigs.getDefaultSuccessMessage(),
                profilesDTO,
                new HashMap<>()
                );
    }
}
