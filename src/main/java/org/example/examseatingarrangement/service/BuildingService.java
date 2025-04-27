package org.example.examseatingarrangement.service;

import org.example.examseatingarrangement.model.Building;
import org.example.examseatingarrangement.model.Room;

import java.util.List;

public interface BuildingService {
    List<Building> findAll();
    Building findById(Long id);
    Building save(Building building);
    void deleteById(Long id);
    Room findRoomByName(String roomA, Long buildingId);
}