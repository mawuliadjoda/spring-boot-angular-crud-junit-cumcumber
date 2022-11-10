package com.esprit.springbootcrud.service;


import com.esprit.springbootcrud.dto.Kata;
import com.esprit.springbootcrud.entity.KataEntity;
import com.esprit.springbootcrud.repository.KataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KataService {

    private final KataRepository kataRepository;

    @Autowired
    public KataService(KataRepository kataRepository) {
        this.kataRepository = kataRepository;
    }

    public List<Kata> findAll() {
        return this.kataRepository.findAll()
                .stream()
                .map(KataEntity::toDomainKata)
                .collect(Collectors.toList());
    }


    public Kata save(Kata kata) {
        KataEntity kataEntity = new KataEntity();
        kataEntity.setName(kata.getName());
        return this.kataRepository.saveAndFlush(kataEntity).toDomainKata();
    }
}