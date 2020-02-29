package com.skills.rentaride.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skills.rentaride.configs.ApplicationConfigs;
import com.skills.rentaride.dtos.requests.CreateItemTypeDTO;
import com.skills.rentaride.dtos.requests.CreateUserDTO;
import com.skills.rentaride.dtos.requests.EditItemTypeDTO;
import com.skills.rentaride.dtos.responses.ProfilesDTO;
import com.skills.rentaride.dtos.responses.ResponseDTO;
import com.skills.rentaride.entites.CustomersEntity;
import com.skills.rentaride.entites.ItemTypeEntity;
import com.skills.rentaride.entites.ProfilesEntity;
import com.skills.rentaride.exceptions.GenericException;
import com.skills.rentaride.exceptions.InvalidPinStatusException;
import com.skills.rentaride.exceptions.ItemTypeNotFoundException;
import com.skills.rentaride.exceptions.ProfileNotFoundException;
import com.skills.rentaride.utilities.Utils;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Date;
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
public class ItemsService {
    private StorageService storageService;
    private Utils utils;
    private ApplicationConfigs applicationConfigs;
    private final Logger logger;
    private final ObjectMapper objectMapper;
    private final ModelMapper modelMapper;

    public ResponseDTO createUser(CreateUserDTO createUserDTO) throws JsonProcessingException, InvalidPinStatusException, GenericException {
        CustomersEntity customersEntity = utils.createCustomerObject(createUserDTO);
        storageService.persistCustomersEntity(customersEntity);
        ProfilesEntity profilesEntity = utils.createProfileObject(createUserDTO, customersEntity);
        logger.info("Profiles Entity to save::{}", objectMapper.writeValueAsString(profilesEntity));
        ProfilesEntity newP = storageService.persistProfilesEntity(profilesEntity);
        //Generate One Time Pin
        utils.generateOTP(newP);
        storageService.persistProfilesEntity(newP);

        return utils.formulateResponse(
                applicationConfigs.getSuccessStatusCode(),
                applicationConfigs.getDefaultSuccessMessage(),
                utils.mapProfileDetails(profilesEntity),
                new HashMap<>()
        );
    }

    public ResponseDTO fetchItemType() throws JsonProcessingException {
        List<ItemTypeEntity> itemTypeEntityList = storageService.fetchItemTypes();
        logger.info("Item type list::{}", objectMapper.writeValueAsString(itemTypeEntityList));

        return utils.formulateResponse(
                applicationConfigs.getSuccessStatusCode(),
                applicationConfigs.getDefaultSuccessMessage(),
                itemTypeEntityList,
                new HashMap<>()
                );
    }

    public ResponseDTO createItemType(CreateItemTypeDTO createItemTypeDTO) {
        ItemTypeEntity itemTypeEntity = storageService.persistItemType(modelMapper.map(createItemTypeDTO, ItemTypeEntity.class));
            return utils.formulateResponse(
                    applicationConfigs.getSuccessStatusCode(),
                    applicationConfigs.getDefaultSuccessMessage(),
                    itemTypeEntity,
                    new HashMap<>()
            );
        }

    public ResponseDTO updateItemType(EditItemTypeDTO editItemTypeDTO) throws ItemTypeNotFoundException {
        ItemTypeEntity itemTypeEntity = storageService.findItemTypeByID(editItemTypeDTO.getLendItemTypeID());
        itemTypeEntity.setDateModified(new Date());
        if(!StringUtils.isEmpty(editItemTypeDTO.getLendItemTypeName())){
            itemTypeEntity.setLendItemTypeName(editItemTypeDTO.getLendItemTypeName());
        }
        if(editItemTypeDTO.getLendItemTypeCost()!=null){
            itemTypeEntity.setLendItemCost(editItemTypeDTO.getLendItemTypeCost());
        }
        storageService.persistItemType(itemTypeEntity);

        return utils.formulateResponse(
                applicationConfigs.getSuccessStatusCode(),
                applicationConfigs.getDefaultSuccessMessage(),
                itemTypeEntity,
                new HashMap<>()
        );
    }
}
