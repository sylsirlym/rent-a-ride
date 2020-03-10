package com.skills.rentaride.entites;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sylvester
 * Project rent-a-ride
 * User: sylvester
 * Date: 2/25/20
 * Time: 12:19 AM
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "lendItemOwnerProfiles")
@Where(clause = "active='1'")
public class ItemOwnerProfilesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lendItemOwnerProfileID;
    private String idNumber;
    private String mobileNumber;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @UpdateTimestamp
    private Date dateModified;

    @OneToOne()
    @JoinColumn(name = "customerID", nullable = false)
    private CustomersEntity customersEntity;

}
