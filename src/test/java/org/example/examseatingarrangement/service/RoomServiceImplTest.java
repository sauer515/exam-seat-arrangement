package org.example.examseatingarrangement.service;

import org.example.examseatingarrangement.model.Building;
import org.example.examseatingarrangement.model.Room;
import org.example.examseatingarrangement.repository.RoomRepository;
import org.example.examseatingarrangement.service.impl.RoomServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoomServiceImplTest {

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomServiceImpl roomService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        Room room = new Room("Room A", 100, new Building("Building A"));
        when(roomRepository.save(room)).thenReturn(room);

        Room result = roomService.save(room);

        assertNotNull(result);
        assertEquals("Room A", result.getName());
        verify(roomRepository, times(1)).save(room);
    }

    @Test
    void testFindById() {
        Room room = new Room("Room A", 100, new Building("Building A"));
        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));

        Room result = roomService.findById(1L);

        assertNotNull(result);
        assertEquals("Room A", result.getName());
        verify(roomRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteById() {
        doNothing().when(roomRepository).deleteById(1L);

        roomService.deleteById(1L);

        verify(roomRepository, times(1)).deleteById(1L);
    }
}