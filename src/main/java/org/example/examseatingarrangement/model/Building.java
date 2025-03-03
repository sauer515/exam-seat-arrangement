package org.example.examseatingarrangement.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="buildings")
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Room> rooms;

    public Building() {}

    public Building(String name) {
        this.name = name;
        rooms = new ArrayList<>();
    }

    public Room findRoomWithEnoughSeats(int studentNumber) {
        return rooms.stream()
                .filter(room -> room.getCapacity()>studentNumber)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No room left with enough capacity"));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        if (rooms.contains(room)) throw new EntityExistsException("Room already added");
        rooms.add(room);
    }

    public Room findRoomByName(String roomName) {
        return rooms.stream().filter(room -> roomName.equals(room.getName())).findFirst().orElse(null);
    }

    public void deleteRoom(Room room) {
        if (!rooms.contains(room)) throw new EntityNotFoundException("Room not found in the building");
        rooms.remove(room);
    }
}
