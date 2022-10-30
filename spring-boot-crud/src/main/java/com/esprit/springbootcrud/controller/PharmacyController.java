package com.esprit.springbootcrud.controller;

import com.esprit.springbootcrud.dto.PharmacyDTO;
import com.esprit.springbootcrud.service.PharmacyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pharmacies")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class PharmacyController {
    private final PharmacyService pharmacyService;

    @GetMapping()
    public ResponseEntity<List<PharmacyDTO>> getAll() {
        return new ResponseEntity<>(pharmacyService.findAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<PharmacyDTO> add(@RequestBody PharmacyDTO pharmacy) {
        return new ResponseEntity<>(pharmacyService.save(pharmacy), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PharmacyDTO> update(@RequestBody PharmacyDTO pharmacy) {

        return new ResponseEntity<>(pharmacyService.update(pharmacy), HttpStatus.OK);
    }

    public void delete(@PathVariable Long id) {
        pharmacyService.delete(id);
    }
}
