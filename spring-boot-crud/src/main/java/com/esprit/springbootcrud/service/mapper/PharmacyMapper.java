package com.esprit.springbootcrud.service.mapper;

import com.esprit.springbootcrud.dto.PharmacyDTO;
import com.esprit.springbootcrud.entity.Pharmacy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PharmacyMapper {
    Pharmacy mapToEntity(PharmacyDTO pharmacyDTO);
    PharmacyDTO mapToDTO(Pharmacy pharmacy);
}
