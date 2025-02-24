package org.example.examseatingarrangement.controller;

import org.example.examseatingarrangement.model.Building;
import org.example.examseatingarrangement.service.BuildingService;
import org.example.examseatingarrangement.service.impl.BuildingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/building")
public class BuildingController {

    @Autowired
    private BuildingServiceImpl buildingService;

    @GetMapping
    public List<Building> getAllBuildings() {
        return buildingService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Building> getBuildingById(@PathVariable Long id) {
        Building building = buildingService.findById(id);
        if (building == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(building);
        }
    }

    @PostMapping
    public Building addBuilding(@RequestParam String name) {
        Building building = new Building(name);
        return buildingService.save(building);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBuilding(@PathVariable Long id) {
        Building building = buildingService.findById(id);
        if (building == null) {
            return ResponseEntity.notFound().build();
        }
        buildingService.deleteById(id);
        return ResponseEntity.ok("Building deleted successfully");
    }
}
