package com.skills.rentaride.repository;

import com.skills.rentaride.entites.CustomersEntity;
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
public interface CustomersRepository extends JpaRepository<CustomersEntity,Integer> {
}
