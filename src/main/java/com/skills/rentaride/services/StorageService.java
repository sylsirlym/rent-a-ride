package com.skills.rentaride.services;

import com.skills.rentaride.entites.CustomersEntity;
import com.skills.rentaride.entites.ProfilesEntity;
import com.skills.rentaride.exceptions.ProfileNotFoundException;
import com.skills.rentaride.repository.CustomersRepository;
import com.skills.rentaride.repository.ProfilesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

    public CustomersEntity persistCustomersEntity(CustomersEntity customersEntity) {
            return customersRepository.save(customersEntity);
    }

    public ProfilesEntity persistProfilesEntity(ProfilesEntity profilesEntity) {
            return profilesRepository.save(profilesEntity);
    }

    public ProfilesEntity fetchProfileByMsisdn(String msisdn) throws ProfileNotFoundException {
        return profilesRepository.findProfilesEntityByMsisdn(msisdn).orElseThrow(() -> new ProfileNotFoundException("Profile not found"));
    }
}
