package com.esprit.springbootcrud.entity;


import com.esprit.springbootcrud.dto.Kata;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Kata")
@Data
public class KataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    public Kata toDomainKata(){
        return new Kata(this.name);
    }

}
