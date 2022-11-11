package com.esprit.springbootcrud.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PharmacyDTO {
    private Long id;

    private String name;

    private String address;

    private Double lat;

    private Double lng;
}
