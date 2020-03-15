package com.skills.rentaride.services;

import com.skills.rentaride.entites.*;
import com.skills.rentaride.exceptions.*;
import com.skills.rentaride.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lilian
 * Project rent-a-ride
 * User: lilian
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

    /**
     * Persist customers entity customers entity.
     *
     * @param customersEntity the customers entity
     * @return the customers entity
     */
    public CustomersEntity persistCustomersEntity(CustomersEntity customersEntity) {
            return customersRepository.save(customersEntity);
    }

    /**
     * Persist profiles entity profiles entity.
     *
     * @param profilesEntity the profiles entity
     * @return the profiles entity
     */
    public ProfilesEntity persistProfilesEntity(ProfilesEntity profilesEntity) {
            return profilesRepository.save(profilesEntity);
    }

    /**
     * Fetch profile by msisdn profiles entity.
     *
     * @param msisdn the msisdn
     * @return the profiles entity
     * @throws ProfileNotFoundException the profile not found exception
     */
    public ProfilesEntity fetchProfileByMsisdn(String msisdn) throws ProfileNotFoundException {
        return profilesRepository.findProfilesEntityByMsisdn("25479234235").
                orElseThrow(() -> new ProfileNotFoundException("Profile not found"));
    }

    /**
     * Fetch profile by id profiles entity.
     *
     * @param id the id
     * @return the profiles entity
     * @throws ProfileNotFoundException the profile not found exception
     */
    public ProfilesEntity fetchProfileByID(int id) throws ProfileNotFoundException {
        return profilesRepository.findById(id).orElseThrow(() -> new ProfileNotFoundException("Profile not found"));
    }

    /**
     * Persist item type item type entity.
     *
     * @param itemTypeEntity the item type entity
     * @return the item type entity
     */
    public ItemTypeEntity persistItemType(ItemTypeEntity itemTypeEntity) {
        return itemTypeRepository.save(itemTypeEntity);
    }

    /**
     * Persist item items entity.
     *
     * @param itemsEntity the items entity
     * @return the items entity
     */
    public ItemsEntity persistItem(ItemsEntity itemsEntity) {
        return itemsRepository.save(itemsEntity);
    }

    /**
     * Fetch item types list.
     *
     * @return the list
     */
    public List<ItemTypeEntity> fetchItemTypes() {
        return itemTypeRepository.findAll();
    }

    /**
     * Find item type by id item type entity.
     *
     * @param lendItemTypeID the lend item type id
     * @return the item type entity
     * @throws ItemTypeNotFoundException the item type not found exception
     */
    public ItemTypeEntity findItemTypeByID(int lendItemTypeID) throws ItemTypeNotFoundException {
        return itemTypeRepository.findById(lendItemTypeID).orElseThrow(() -> new ItemTypeNotFoundException("Item type not found"));
    }

    /**
     * Find item owner by id item owner profiles entity.
     *
     * @param itemOwnerID the item owner id
     * @return the item owner profiles entity
     * @throws ItemOwnerNotFoundException the item owner not found exception
     */
    public ItemOwnerProfilesEntity findItemOwnerByID(int itemOwnerID) throws ItemOwnerNotFoundException {
        return itemOwnerProfilesRepository.findById(itemOwnerID).orElseThrow(() -> new ItemOwnerNotFoundException("Item owner not found"));
    }

    /**
     * Fetch items list.
     *
     * @return the list
     */
    public List<ItemsEntity> fetchItems() { return itemsRepository.findAll();
    }

    /**
     * Fetch item by id items entity.
     *
     * @param itemID the item id
     * @return the items entity
     * @throws ItemNotFoundException the item not found exception
     */
    public ItemsEntity fetchItemByID(int itemID) throws ItemNotFoundException {
        return itemsRepository.findById(itemID).orElseThrow(()->new ItemNotFoundException("Item with ID "+itemID+" not found"));
    }

    /**
     * Fetch lend status by id lend transaction status entity.
     *
     * @param status the status
     * @return the lend transaction status entity
     * @throws LendTxnStatusNotFoundException the lend txn status not found exception
     */
    public LendTransactionStatusEntity fetchLendStatusByID(int status) throws LendTxnStatusNotFoundException {
        return lendTransactionStatusRepository.findById(status).orElseThrow(()->new LendTxnStatusNotFoundException("Status with ID "+status+" not found"));
    }
}
