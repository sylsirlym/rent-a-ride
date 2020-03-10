package com.skills.rentaride.entites;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
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
@Table(name = "lendItems")
@Where(clause = "active='1'")
public class ItemsEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lendItemID;
    private String serialNumber;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @UpdateTimestamp
    private Date dateModified;


    @ManyToOne()
    @JoinColumn(name = "lendItemType", nullable = false)
    private ItemTypeEntity itemTypeEntity;

    @OneToOne()
    @JoinColumn(name = "lendItemOwnerProfileID", nullable = false)
    private ItemOwnerProfilesEntity itemOwnerProfileEntity;
}
