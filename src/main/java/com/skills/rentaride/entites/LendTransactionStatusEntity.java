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
@Table(name = "lendTransactionStatus")
@Where(clause = "active='1'")
public class LendTransactionStatusEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lendTransactionStatusID;
    private String lendTransactionStatus;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @UpdateTimestamp
    private Date dateModified;
}
