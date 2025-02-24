package org.example.examseatingarrangement.service.impl;

import org.example.examseatingarrangement.model.Building;
import org.example.examseatingarrangement.repository.BuildingRepository;
import org.example.examseatingarrangement.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Override
    public List<Building> findAll() {
        return buildingRepository.findAll();
    }

    @Override
    public Building findById(Long id) {
        return buildingRepository.findById(id).orElse(null);
    }

    @Override
    public Building save(Building building) {
        return buildingRepository.save(building);
    }

    @Override
    public void deleteById(Long id) {
        buildingRepository.deleteById(id);
    }
}