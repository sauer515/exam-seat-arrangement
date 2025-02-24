package org.example.examseatingarrangement.service;

import org.example.examseatingarrangement.model.Room;

import java.util.List;

public interface RoomService {
    List<Room> findAll();
    Room findById(Long id);
    Room save(Room room);
    void deleteById(Long id);
}