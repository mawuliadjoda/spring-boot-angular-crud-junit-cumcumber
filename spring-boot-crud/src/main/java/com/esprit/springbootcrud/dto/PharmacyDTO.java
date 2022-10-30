package com.esprit.springbootcrud.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
@Getter
@RequiredArgsConstructor
public class PharmacyDTO {
    private Long id;

    private String name;

    private String address;

    private Double lat;

    private Double lng;
}
