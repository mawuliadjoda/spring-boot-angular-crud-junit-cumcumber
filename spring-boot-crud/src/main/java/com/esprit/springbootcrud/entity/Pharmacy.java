package com.esprit.springbootcrud.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "pharmacy")
@Data
public class Pharmacy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String address;

    private Double lat;

    private Double lng;
}
