package com.skills.rentaride.services;

import com.skills.rentaride.configs.ApplicationConfigs;
import com.skills.rentaride.dtos.requests.LendRequestDTO;
import com.skills.rentaride.dtos.responses.ResponseDTO;
import com.skills.rentaride.entites.LendTransactionsEntity;
import com.skills.rentaride.exceptions.ItemNotFoundException;
import com.skills.rentaride.exceptions.LendTxnStatusNotFoundException;
import com.skills.rentaride.exceptions.ProfileNotFoundException;
import com.skills.rentaride.utilities.Utils;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Created by sylvester
 * Project rent-a-ride
 * User: sylvester
 * Date: 3/11/20
 * Time: 1:19 AM
 */
@Service
@AllArgsConstructor
public class LendingService {
    private StorageService storageService;
    private Utils utils;
    private ApplicationConfigs applicationConfigs;
    private final Logger logger;

    public ResponseDTO requestItem(LendRequestDTO lendRequestDTO) throws ProfileNotFoundException, ItemNotFoundException, LendTxnStatusNotFoundException {
        LendTransactionsEntity lendTransactionsEntity = new LendTransactionsEntity();
        lendTransactionsEntity.setItemsEntity(storageService.fetchItemByID(lendRequestDTO.getLendItemID()));
        lendTransactionsEntity.setProfilesEntity(storageService.fetchProfileByID(lendRequestDTO.getProfileID()));
        lendTransactionsEntity.setLendTransactionStatusEntity(storageService.fetchLendStatusByID(applicationConfigs.getPendingStatus()));
        return utils.formulateResponse(
                applicationConfigs.getSuccessStatusCode(),
                applicationConfigs.getDefaultSuccessMessage(),
                lendTransactionsEntity,
                new HashMap<>()
        );
    }
}
