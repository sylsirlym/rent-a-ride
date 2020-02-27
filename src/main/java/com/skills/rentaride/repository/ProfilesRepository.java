package com.skills.rentaride.repository;

import com.skills.rentaride.entites.ProfilesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by sylvester
 * Project rent-a-ride
 * User: sylvester
 * Date: 2/25/20
 * Time: 1:04 AM
 */
@Repository
public interface ProfilesRepository extends JpaRepository<ProfilesEntity,Integer> {
   Optional<ProfilesEntity> findProfilesEntityByMsisdn(String msisdn);
}
