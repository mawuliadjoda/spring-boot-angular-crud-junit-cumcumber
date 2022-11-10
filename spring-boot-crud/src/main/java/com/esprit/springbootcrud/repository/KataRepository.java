package com.esprit.springbootcrud.repository;

import com.esprit.springbootcrud.entity.KataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KataRepository extends JpaRepository<KataEntity, Long> {
}