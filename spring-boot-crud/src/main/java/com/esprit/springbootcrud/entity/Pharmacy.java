package com.esprit.springbootcrud.entity;


import lombok.*;

import javax.persistence.*;





@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "pharmacy")
public class Pharmacy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private Double lat;

    private Double lng;
}
