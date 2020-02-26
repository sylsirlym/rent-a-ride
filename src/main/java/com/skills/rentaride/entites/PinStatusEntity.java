package com.skills.rentaride.entites;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Created by sylvester
 * Project rent-a-ride
 * User: sylvester
 * Date: 2/25/20
 * Time: 10:29 PM
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "pinStatus")
@Where(clause = "active='1'")
public class PinStatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pinStatusID;
    private String pinStatus;
}
