package com.skills.rentaride.entites;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lilian
 * Project rent-a-ride
 * User: lilian
 * Date: 2/25/20
 * Time: 12:19 AM
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "profiles")
@Where(clause = "active='1'")
public class ProfilesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int profileID;
    private String identificationNumber;
    private String msisdn;
    private String pinHash;
    private int pinRetries;
    private int identificationTypeID;
    @UpdateTimestamp
    private Date dateLastAccessed;
    private Date dateTempLocked;
    private Date datePinChanged;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @UpdateTimestamp
    private Date dateModified;

    @OneToOne()
    @JoinColumn(name = "customerID", nullable = false)
    private CustomersEntity customersEntity;

    @OneToOne()
    @JoinColumn(name = "pinStatus", nullable = false)
    private PinStatusEntity pinStatusEntity;

}
