package com.skills.rentaride.services;

import com.skills.rentaride.entites.*;
import com.skills.rentaride.exceptions.*;
import com.skills.rentaride.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sylvester
 * Project rent-a-ride
 * User: sylvester
 * Date: 2/27/20
 * Time: 7:51 PM
 */
@Service
@AllArgsConstructor
public class StorageService {
    private ProfilesRepository profilesRepository;
    private CustomersRepository customersRepository;
    private ItemTypeRepository itemTypeRepository;
    private ItemsRepository itemsRepository;
    private ItemOwnerProfilesRepository itemOwnerProfilesRepository;
    private LendTransactionsRepository lendTransactionsRepository;
    private LendTransactionStatusRepository lendTransactionStatusRepository;

    public CustomersEntity persistCustomersEntity(CustomersEntity customersEntity) {
            return customersRepository.save(customersEntity);
    }

    public ProfilesEntity persistProfilesEntity(ProfilesEntity profilesEntity) {
            return profilesRepository.save(profilesEntity);
    }

    public ProfilesEntity fetchProfileByMsisdn(String msisdn) throws ProfileNotFoundException {
        return profilesRepository.findProfilesEntityByMsisdn(msisdn).orElseThrow(() -> new ProfileNotFoundException("Profile not found"));
    }
    public ProfilesEntity fetchProfileByID(int id) throws ProfileNotFoundException {
        return profilesRepository.findById(id).orElseThrow(() -> new ProfileNotFoundException("Profile not found"));
    }

    public ItemTypeEntity persistItemType(ItemTypeEntity itemTypeEntity) {
        return itemTypeRepository.save(itemTypeEntity);
    }

    public ItemsEntity persistItem(ItemsEntity itemsEntity) {
        return itemsRepository.save(itemsEntity);
    }
    public List<ItemTypeEntity> fetchItemTypes() {
        return itemTypeRepository.findAll();
    }

    public ItemTypeEntity findItemTypeByID(int lendItemTypeID) throws ItemTypeNotFoundException {
        return itemTypeRepository.findById(lendItemTypeID).orElseThrow(() -> new ItemTypeNotFoundException("Item type not found"));
    }

    public ItemOwnerProfilesEntity findItemOwnerByID(int itemOwnerID) throws ItemOwnerNotFoundException {
        return itemOwnerProfilesRepository.findById(itemOwnerID).orElseThrow(() -> new ItemOwnerNotFoundException("Item owner not found"));
    }

    public List<ItemsEntity> fetchItems() { return itemsRepository.findAll();
    }

    public ItemsEntity fetchItemByID(int itemID) throws ItemNotFoundException {
        return itemsRepository.findById(itemID).orElseThrow(()->new ItemNotFoundException("Item with ID "+itemID+" not found"));
    }

    public LendTransactionStatusEntity fetchLendStatusByID(int status) throws LendTxnStatusNotFoundException {
        return lendTransactionStatusRepository.findById(status).orElseThrow(()->new LendTxnStatusNotFoundException("Status with ID "+status+" not found"));
    }
}
