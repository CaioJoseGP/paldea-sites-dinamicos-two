package com.jardim.paldea.repository;

import com.jardim.paldea.model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantRepository extends JpaRepository<Plant, Long> {
}
