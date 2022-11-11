package com.esprit.springbootcrud.service.impl;


import com.esprit.springbootcrud.dto.PharmacyDTO;
import com.esprit.springbootcrud.entity.Pharmacy;
import com.esprit.springbootcrud.repository.PharmacyRepository;
import com.esprit.springbootcrud.service.mapper.PharmacyMapper;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
class PharmacyServiceImplTest {
    @InjectMocks
    private PharmacyServiceImpl pharmacyService;

    @Mock
    private PharmacyRepository pharmacyRepository;

    @Mock
    private PharmacyMapper pharmacyMapper;

    @Test
    void find_all_pharmacies_must_return_all_pharmacies() {
        // Given
        List<PharmacyDTO> existingPharmacies = List.of(
                PharmacyDTO.builder()
                        .id(1L)
                        .name("Pharmacie du boulevard")
                        .address("test")
                        .lng(10.2)
                        .lat(12.3)
                        .build()
        );
        // When
        List<Pharmacy> pharmaciesEnTities = pharmacyMapper.mapToEntities(existingPharmacies);
        Mockito.when(pharmacyMapper.mapToDTOS(pharmaciesEnTities)).thenReturn(existingPharmacies);

        Mockito.when(pharmacyRepository.findAll()).thenReturn(pharmaciesEnTities);

        List<com.esprit.springbootcrud.dto.PharmacyDTO> pharmacies = pharmacyService.findAll();
        Mockito.verify(pharmacyRepository, Mockito.times(1)).findAll();


        Assertions.assertThat(existingPharmacies).containsExactlyElementsOf(pharmacies);

    }


}