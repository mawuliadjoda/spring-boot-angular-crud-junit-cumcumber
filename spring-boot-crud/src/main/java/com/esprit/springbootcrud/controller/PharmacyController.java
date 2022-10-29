package com.esprit.springbootcrud.controller;

import com.esprit.springbootcrud.entity.Pharmacy;
import com.esprit.springbootcrud.exception.MyCrudException;
import com.esprit.springbootcrud.exception.MyCrudExceptionEnum;
import com.esprit.springbootcrud.repository.PharmacyRepository;
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

    private final PharmacyRepository pharmacyRepository;

    @GetMapping()
    public ResponseEntity<List<Pharmacy>> getAll() {
        return new ResponseEntity<>(pharmacyRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Pharmacy> add(@RequestBody Pharmacy pharmacy) {
        return new ResponseEntity<>(pharmacyRepository.save(pharmacy), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Pharmacy> update(@PathVariable Long id, @RequestBody Pharmacy pharmacy) {
        if (pharmacyRepository.existsById(id)) {
            throw new MyCrudException(MyCrudExceptionEnum.PHARMACY_NOT_FOUND, "No solution founded with id {}");
        }
        return new ResponseEntity<>(pharmacyRepository.save(pharmacy), HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        try {
            pharmacyRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
