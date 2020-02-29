package com.skills.rentaride.services;

import com.skills.rentaride.entites.CustomersEntity;
import com.skills.rentaride.entites.ItemTypeEntity;
import com.skills.rentaride.entites.ProfilesEntity;
import com.skills.rentaride.exceptions.ItemTypeNotFoundException;
import com.skills.rentaride.exceptions.ProfileNotFoundException;
import com.skills.rentaride.repository.CustomersRepository;
import com.skills.rentaride.repository.ItemTypeRepository;
import com.skills.rentaride.repository.ProfilesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public CustomersEntity persistCustomersEntity(CustomersEntity customersEntity) {
            return customersRepository.save(customersEntity);
    }

    public ProfilesEntity persistProfilesEntity(ProfilesEntity profilesEntity) {
            return profilesRepository.save(profilesEntity);
    }

    public ProfilesEntity fetchProfileByMsisdn(String msisdn) throws ProfileNotFoundException {
        return profilesRepository.findProfilesEntityByMsisdn(msisdn).orElseThrow(() -> new ProfileNotFoundException("Profile not found"));
    }

    public ItemTypeEntity persistItemType(ItemTypeEntity itemTypeEntity) {
        return itemTypeRepository.save(itemTypeEntity);
    }

    public List<ItemTypeEntity> fetchItemTypes() {
        return itemTypeRepository.findAll();
    }

    public ItemTypeEntity findItemTypeByID(int lendItemTypeID) throws ItemTypeNotFoundException {
        return itemTypeRepository.findItemTypeEntitiesByLendItemTypeID(lendItemTypeID).orElseThrow(() -> new ItemTypeNotFoundException("Item type not found"));
    }
}
