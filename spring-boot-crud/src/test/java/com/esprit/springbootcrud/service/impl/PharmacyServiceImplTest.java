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
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

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
        List<Pharmacy> pharmaciesEnTities = pharmacyMapper.mapToEntities(existingPharmacies);


        // When
        when(pharmacyMapper.mapToDTOS(pharmaciesEnTities)).thenReturn(existingPharmacies);
        when(pharmacyRepository.findAll()).thenReturn(pharmaciesEnTities);

        // Test
        List<com.esprit.springbootcrud.dto.PharmacyDTO> pharmacies = pharmacyService.findAll();

        // Then
        verify(pharmacyRepository, times(1)).findAll();
        Assertions.assertThat(existingPharmacies).containsExactlyElementsOf(pharmacies);

    }


}