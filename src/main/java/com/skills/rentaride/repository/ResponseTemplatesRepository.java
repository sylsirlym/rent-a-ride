package com.skills.rentaride.repository;

import com.skills.rentaride.entites.CustomersEntity;
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
public interface ResponseTemplatesRepository extends JpaRepository<ResponseTemplatesEntity,Integer> {
    /**
     * Find by status code response templates entity.
     *
     * @param statusCode the status code
     * @return the response templates entity
     */
    ResponseTemplatesEntity findByStatusCode(Integer statusCode);
}
