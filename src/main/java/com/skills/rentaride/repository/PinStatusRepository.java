package com.skills.rentaride.repository;

import com.skills.rentaride.entites.PinStatusEntity;
import com.skills.rentaride.entites.ResponseTemplatesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lilian
 * Project rent-a-ride
 * User: lilian
 * Date: 2/25/20
 * Time: 1:04 AM
 */
@Repository
public interface PinStatusRepository extends JpaRepository<PinStatusEntity,Integer> {
    /**
     * Find by pin status pin status entity.
     *
     * @param pinStatus the pin status
     * @return the pin status entity
     */
    PinStatusEntity findByPinStatus(String pinStatus);
}
