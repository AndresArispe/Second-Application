package com.milankas.training.companyapi.persistance.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String addressLine1;
    private String addressLine2;
    private String state;
    private String city;
    private String zipCode;
    private String countryCode;

    @JsonIgnore
    @OneToOne
    private Company company;

}
