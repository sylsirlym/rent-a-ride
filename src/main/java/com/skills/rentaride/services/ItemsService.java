package com.skills.rentaride.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skills.rentaride.configs.ApplicationConfigs;
import com.skills.rentaride.dtos.requests.CreateItemDTO;
import com.skills.rentaride.dtos.requests.CreateItemTypeDTO;
import com.skills.rentaride.dtos.requests.CreateUserDTO;
import com.skills.rentaride.dtos.requests.EditItemTypeDTO;
import com.skills.rentaride.dtos.responses.ProfilesDTO;
import com.skills.rentaride.dtos.responses.ResponseDTO;
import com.skills.rentaride.entites.CustomersEntity;
import com.skills.rentaride.entites.ItemTypeEntity;
import com.skills.rentaride.entites.ItemsEntity;
import com.skills.rentaride.entites.ProfilesEntity;
import com.skills.rentaride.exceptions.*;
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
 * Created by lilian
 * Project rent-a-ride
 * User: lilian
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

    /**
     * Create item response dto.
     *
     * @param createItemDTO the create item dto
     * @return the response dto
     * @throws ItemTypeNotFoundException  the item type not found exception
     * @throws ItemOwnerNotFoundException the item owner not found exception
     */
    public ResponseDTO createItem(CreateItemDTO createItemDTO) throws ItemTypeNotFoundException, ItemOwnerNotFoundException {
        ItemsEntity itemsEntity = new ItemsEntity();
        itemsEntity.setSerialNumber(createItemDTO.getSerialNumber());
        itemsEntity.setItemTypeEntity(storageService.findItemTypeByID(createItemDTO.getLendItemType()));
        itemsEntity.setItemOwnerProfileEntity(storageService.findItemOwnerByID(createItemDTO.getLendItemOwnerProfileID()));
        storageService.persistItem(itemsEntity);
        return utils.formulateResponse(
                applicationConfigs.getSuccessStatusCode(),
                applicationConfigs.getDefaultSuccessMessage(),
                itemsEntity,
                new HashMap<>()
        );
    }

    /**
     * Fetch items response dto.
     *
     * @return the response dto
     * @throws JsonProcessingException the json processing exception
     */
    public ResponseDTO fetchItems() throws JsonProcessingException {
        List<ItemsEntity> itemEntityList = storageService.fetchItems();
        logger.info("Items list::{}", objectMapper.writeValueAsString(itemEntityList));

        return utils.formulateResponse(
                applicationConfigs.getSuccessStatusCode(),
                applicationConfigs.getDefaultSuccessMessage(),
                itemEntityList,
                new HashMap<>()
        );
    }

    /**
     * Fetch item response dto.
     *
     * @param itemID the item id
     * @return the response dto
     * @throws JsonProcessingException the json processing exception
     * @throws ItemNotFoundException   the item not found exception
     */
    public ResponseDTO fetchItem(int itemID) throws JsonProcessingException, ItemNotFoundException {
        ItemsEntity itemEntity = storageService.fetchItemByID(itemID);
        logger.info("Items list::{}", objectMapper.writeValueAsString(itemEntity));

        return utils.formulateResponse(
                applicationConfigs.getSuccessStatusCode(),
                applicationConfigs.getDefaultSuccessMessage(),
                itemEntity,
                new HashMap<>()
        );
    }


    /**
     * Fetch item type response dto.
     *
     * @return the response dto
     * @throws JsonProcessingException the json processing exception
     */
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

    /**
     * Create item type response dto.
     *
     * @param createItemTypeDTO the create item type dto
     * @return the response dto
     */
    public ResponseDTO createItemType(CreateItemTypeDTO createItemTypeDTO) {
        ItemTypeEntity itemTypeEntity = storageService.persistItemType(modelMapper.map(createItemTypeDTO, ItemTypeEntity.class));
            return utils.formulateResponse(
                    applicationConfigs.getSuccessStatusCode(),
                    applicationConfigs.getDefaultSuccessMessage(),
                    itemTypeEntity,
                    new HashMap<>()
            );
        }

    /**
     * Update item type response dto.
     *
     * @param editItemTypeDTO the edit item type dto
     * @return the response dto
     * @throws ItemTypeNotFoundException the item type not found exception
     */
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
