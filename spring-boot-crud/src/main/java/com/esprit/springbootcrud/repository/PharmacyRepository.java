package com.esprit.springbootcrud.repository;

import com.esprit.springbootcrud.entity.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
}
