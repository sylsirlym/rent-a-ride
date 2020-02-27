package com.skills.rentaride.utilities;

import com.skills.rentaride.configs.ApplicationConfigs;
import com.skills.rentaride.dtos.requests.CreateUserDTO;
import com.skills.rentaride.dtos.responses.ProfilesDTO;
import com.skills.rentaride.dtos.responses.ResponseDTO;
import com.skills.rentaride.entites.CustomersEntity;
import com.skills.rentaride.entites.PinStatusEntity;
import com.skills.rentaride.entites.ProfilesEntity;
import com.skills.rentaride.entites.ResponseTemplatesEntity;
import com.skills.rentaride.exceptions.GenericException;
import com.skills.rentaride.exceptions.InvalidPinStatusException;
import com.skills.rentaride.repository.PinStatusRepository;
import com.skills.rentaride.repository.ResponseTemplatesRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by sylvester
 * Project rent-a-ride
 * User: sylvester
 * Date: 2/25/20
 * Time: 1:30 AM
 */
@Component
@AllArgsConstructor
public class Utils {
    private ResponseTemplatesRepository responseTemplatesRepository;
    private PinStatusRepository pinStatusRepository;
    private ApplicationConfigs applicationConfigs;
    private ModelMapper modelMapper;
    private static Random random = new Random();

    private static final Logger logger = LogManager
            .getLogger(Utils.class);

    public ProfilesEntity createProfileObject(CreateUserDTO createUserDTO, CustomersEntity customersEntity) {
        ProfilesEntity profilesEntity = new ProfilesEntity();
        profilesEntity.setMsisdn(createUserDTO.getMsisdn());
        profilesEntity.setIdentificationNumber(createUserDTO.getIdentificationNumber());
        profilesEntity.setIdentificationTypeID(createUserDTO.getIdentificationType());
        profilesEntity.setCustomersEntity(customersEntity);
        profilesEntity.setPinStatusEntity(this.pinStatusRepository.findByPinStatus("inactive"));
        profilesEntity.setDateLastAccessed(new Date());
        profilesEntity.setPinRetries(0);
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

    public ResponseDTO formulateResponse(Integer statusCode, String defaultMessage, Object data, HashMap<String, String> templates){

        return new ResponseDTO(
                statusCode,
                this.fetchStatusMessage(statusCode)!= null
                        ? this.formulateStatusMessage(
                                this.fetchStatusMessage(statusCode), templates)
                        : defaultMessage,
                data instanceof List ? (List<?>)data : List.of(data)
        );
    }

    private String formulateStatusMessage(String templateMessage, HashMap<String, String> hashMap) {
        //Iterate Through to replace message
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (templateMessage.contains(key) && value != null) {
                value = StringUtils.stripAccents(value);
                templateMessage = templateMessage.replace(key, value);
            }
        }
        return templateMessage;
    }

    private String fetchStatusMessage(Integer statusCode) {
        ResponseTemplatesEntity responseTemplatesEntity = responseTemplatesRepository.findByStatusCode(statusCode);
        return responseTemplatesEntity!=null ? responseTemplatesEntity.getResponseTemplate() : null;
    }

    public String generateRandomNumber(int digCount)
    {
        StringBuilder stringBuilder = new StringBuilder(digCount);
        for (int i = 0; i < digCount; i++)
        {
            stringBuilder.append((random.nextInt(10)));
        }

//        return stringBuilder.toString();
        return "123456";
    }

    public void generateOTP(ProfilesEntity profilesEntity) throws InvalidPinStatusException, GenericException {
        //Update Pin Status to OTP
        profilesEntity.setPinStatusEntity(this.getPinStatus(applicationConfigs.getOtpPinStatus()));
        this.updatePinHash(profilesEntity, this.generateRandomNumber(applicationConfigs.getPinLength()));
    }

    public void updatePinHash(ProfilesEntity profilesEntity, String pin) throws GenericException {
        logger.info("About to create pin hash with pin:{}", pin);
        //Use hashing since it is irrevesible
        //Set Pin Hash
        profilesEntity.setPinHash(
                //Concatenate Pin with profile ID for uniqueness
                this.hashPin(profilesEntity.getProfileID() + pin,
                applicationConfigs.getPinHashAlgorithm()));
        profilesEntity.setDatePinChanged(Timestamp.valueOf(LocalDateTime.now()));
    }

    public String hashPin (String plainText, String algo) throws GenericException {
        try {
            logger.info("begining hash process");
            MessageDigest digest = MessageDigest.getInstance(algo);
            byte[] encodedhash = digest.digest(
                    plainText.getBytes(StandardCharsets.UTF_8));

            // convert the byte array to hex
            StringBuilder sb = new StringBuilder();
            for (byte b : encodedhash) {
                sb.append(String.format("%02x", b));
            }

            logger.info("hash completed successfully");
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            logger.error("text hashing failed: {}", ex.getMessage());
            throw new GenericException("Text Hashing Failed: " + ex.getMessage());
        }
    }

    private PinStatusEntity getPinStatus(String pinStatus) throws InvalidPinStatusException {
        PinStatusEntity pinStatusEntity = pinStatusRepository.findByPinStatus(pinStatus);
        if (pinStatusEntity!=null){
            return pinStatusEntity;
        }else {
            throw new InvalidPinStatusException("Pin status"+pinStatus+" is invalid");
        }
    }

    public ProfilesDTO mapProfileDetails(ProfilesEntity profilesEntity) {
        return new ProfilesDTO(
                profilesEntity.getPinStatusEntity().getPinStatusID(),
                profilesEntity.getMsisdn(),
                profilesEntity.getCustomersEntity().getFName(),
                profilesEntity.getCustomersEntity().getOtherNames(),
                profilesEntity.getCustomersEntity().getEmailAddress()
        );
    }

    public boolean validatePin(ProfilesEntity profilesEntity, String pin) throws GenericException {
        logger.info("Pin::{}",  profilesEntity.getPinHash());
        logger.info("Pin2::{}",  this.hashPin(profilesEntity.getProfileID() + pin,
                applicationConfigs.getPinHashAlgorithm()));
        return profilesEntity.getPinHash().equals(this.hashPin(profilesEntity.getProfileID() + pin,
                applicationConfigs.getPinHashAlgorithm()));
    }
}
