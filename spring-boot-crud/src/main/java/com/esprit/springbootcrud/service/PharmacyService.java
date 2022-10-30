package com.esprit.springbootcrud.service;

import com.esprit.springbootcrud.dto.PharmacyDTO;

import java.util.List;

public interface PharmacyService {
    List<PharmacyDTO> findAll();
    PharmacyDTO save(PharmacyDTO pharmacyDTO);
    PharmacyDTO update(PharmacyDTO pharmacyDTO);
    void delete(Long id);

}
