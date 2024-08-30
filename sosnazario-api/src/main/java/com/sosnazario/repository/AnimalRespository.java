package com.sosnazario.repository;

import com.sosnazario.model.AnimalModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRespository extends JpaRepository<AnimalModel, Long> {
}