package com.jardim.paldea.model;

import com.jardim.paldea.repository.PlantRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlantCatalog {

    private final PlantRepository plantRepository;

    public PlantCatalog(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    public List<Plant> findAll() {
        return plantRepository.findAll();
    }

    public Plant findById(long id) {
        return plantRepository.findById(id).orElse(null);
    }

    public Plant create(PlantForm plantForm) {
        Plant plant = new Plant();
        plant.setNome(plantForm.cleanNome());
        plant.setDescricao(plantForm.cleanDescricao());
        plant.setCategoria(plantForm.cleanCategoria());
        plant.setPreco(plantForm.priceAsDouble());
        return plantRepository.save(plant);
    }

    public Plant update(long id, PlantForm plantForm) {
        Plant plant = findById(id);
        if (plant == null) {
            return null;
        }

        plant.setNome(plantForm.cleanNome());
        plant.setDescricao(plantForm.cleanDescricao());
        plant.setCategoria(plantForm.cleanCategoria());
        plant.setPreco(plantForm.priceAsDouble());
        return plantRepository.save(plant);
    }

    public boolean delete(long id) {
        Plant plant = findById(id);
        if (plant == null) {
            return false;
        }

        plantRepository.delete(plant);
        return true;
    }
}

