package com.skills.rentaride.repository;

import com.skills.rentaride.entites.ItemOwnerProfilesEntity;
import com.skills.rentaride.entites.ItemsEntity;
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
public interface ItemOwnerProfilesRepository extends JpaRepository<ItemOwnerProfilesEntity,Integer> {
}
