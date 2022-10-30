package com.esprit.springbootcrud.service.impl;

import com.esprit.springbootcrud.dto.PharmacyDTO;
import com.esprit.springbootcrud.entity.Pharmacy;
import com.esprit.springbootcrud.exception.MyCrudException;
import com.esprit.springbootcrud.exception.MyCrudExceptionEnum;
import com.esprit.springbootcrud.repository.PharmacyRepository;
import com.esprit.springbootcrud.service.PharmacyService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PharmacyServiceImpl implements PharmacyService {
    private final PharmacyRepository pharmacyRepository;

    private static final ModelMapper modelMapper =  new ModelMapper();

    @Override
    public List<PharmacyDTO> findAll() {
        return pharmacyRepository.findAll()
                .stream()
                .map(pharmacy -> modelMapper.map(pharmacy, PharmacyDTO.class))
                .toList();
    }

    @Override
    public PharmacyDTO save(PharmacyDTO pharmacyDTO) {
        Pharmacy pharmacy = pharmacyRepository.save(modelMapper.map(pharmacyDTO, Pharmacy.class));
        return modelMapper.map(pharmacy, PharmacyDTO.class);
    }

    @Override
    public PharmacyDTO update(PharmacyDTO pharmacyDTO) {
        if (pharmacyRepository.existsById(pharmacyDTO.getId())) {
            throw new MyCrudException(MyCrudExceptionEnum.PHARMACY_NOT_FOUND, "Pharmacy not found");
        }
        Pharmacy pharmacy = pharmacyRepository.save(modelMapper.map(pharmacyDTO, Pharmacy.class));
        return modelMapper.map(pharmacy, PharmacyDTO.class);
    }

    @Override
    public void delete(Long id) {
        pharmacyRepository.findById(id)
                .orElseThrow(() -> new MyCrudException(MyCrudExceptionEnum.PHARMACY_NOT_FOUND, "Pharmacy not found"));
        pharmacyRepository.deleteById(id);
    }
}
