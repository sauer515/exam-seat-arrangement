package org.example.examseatingarrangement.controller;

import org.example.examseatingarrangement.model.Building;
import org.example.examseatingarrangement.model.Room;
import org.example.examseatingarrangement.service.BuildingService;
import org.example.examseatingarrangement.service.impl.BuildingServiceImpl;
import org.example.examseatingarrangement.service.impl.RoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/building")
public class BuildingController {

    private final BuildingServiceImpl buildingService;
    private final RoomServiceImpl roomService;

    @Autowired
    public BuildingController(BuildingServiceImpl buildingService, RoomServiceImpl roomService) {
        this.buildingService = buildingService;
        this.roomService = roomService;
    }

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

    @PostMapping("/add")
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

    @GetMapping("/{id}/room")
    public ResponseEntity<Room> getRoomFromBuilding(@PathVariable Long id, @RequestParam String roomName) {
        Building building = buildingService.findById(id);
        if (building == null) {
            return ResponseEntity.notFound().build();
        }
        Room room = buildingService.findRoomByName(roomName, id);
        if (room == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(room);
    }

    @PostMapping("/{id}/room")
    public Room addRoomToBuilding(@PathVariable Long id, @RequestBody Room room) {
        Building building = buildingService.findById(id);
        if (building == null) {
            return null;
        }
        room.setBuilding(building);
        building.addRoom(room);
        return roomService.save(room);
    }

    @DeleteMapping("{id}/room")
    public ResponseEntity<String> deleteRoomFromBuilding(@PathVariable Long id, @RequestParam String roomName) {
        Building building = buildingService.findById(id);
        Room room = buildingService.findRoomByName(roomName, id);
        if (room == null) {
            return ResponseEntity.notFound().build();
        }
        //building.deleteRoom(room);
        roomService.deleteById(room.getId());
        return ResponseEntity.ok("Room successfully deleted");
    }
}
