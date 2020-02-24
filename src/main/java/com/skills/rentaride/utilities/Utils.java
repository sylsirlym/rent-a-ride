package com.skills.rentaride.utilities;

import com.skills.rentaride.dtos.requests.CreateUserDTO;
import com.skills.rentaride.entites.CustomersEntity;
import com.skills.rentaride.entites.ProfilesEntity;
import org.springframework.stereotype.Component;

/**
 * Created by sylvester
 * Project rent-a-ride
 * User: sylvester
 * Date: 2/25/20
 * Time: 1:30 AM
 */
@Component
public class Utils {
    public ProfilesEntity createProfileObject(CreateUserDTO createUserDTO, CustomersEntity customersEntity) {
        ProfilesEntity profilesEntity = new ProfilesEntity();
        profilesEntity.setMsisdn(createUserDTO.getMsisdn());
        profilesEntity.setIdentificationNumber(createUserDTO.getIdentificationNumber());
        profilesEntity.setIdentificationTypeID(createUserDTO.getIdentificationType());
        profilesEntity.setCustomersEntity(customersEntity);
        return profilesEntity;
    }

    public CustomersEntity createCustomerObject(CreateUserDTO createUserDTO) {
        CustomersEntity customersEntity = new CustomersEntity();
        customersEntity.setFName(createUserDTO.getFname());
        customersEntity.setOtherNames(createUserDTO.getOtherNames());
        customersEntity.setEmailAddress(createUserDTO.getEmailAddress());
        customersEntity.setDateOfBirth(createUserDTO.getDateOfBirth());
        return customersEntity;
    }
}
