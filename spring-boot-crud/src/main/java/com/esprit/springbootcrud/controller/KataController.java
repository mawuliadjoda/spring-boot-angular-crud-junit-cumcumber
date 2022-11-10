package com.esprit.springbootcrud.controller;

import com.esprit.springbootcrud.dto.Kata;
import com.esprit.springbootcrud.service.KataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/kata")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class KataController {

    private final KataService kataService;


    @PostMapping
    public Kata save(@RequestBody Kata kata) {
        return kataService.save(kata);
    }

    @GetMapping
    public List<Kata> findAll() {
        return kataService.findAll();
    }
}