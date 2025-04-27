package org.example.examseatingarrangement.controller;

import org.example.examseatingarrangement.model.Building;
import org.example.examseatingarrangement.model.Room;
import org.example.examseatingarrangement.service.impl.BuildingServiceImpl;
import org.example.examseatingarrangement.service.impl.RoomServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class BuildingControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BuildingServiceImpl buildingService;

    @Mock
    private RoomServiceImpl roomService;

    @InjectMocks
    private BuildingController buildingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(buildingController).build();
    }

    @Test
    void testGetAllBuildings() throws Exception {
        when(buildingService.findAll()).thenReturn(Arrays.asList(new Building("Building A"), new Building("Building B")));

        mockMvc.perform(get("/api/building"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        verify(buildingService, times(1)).findAll();
    }

    @Test
    void testGetBuildingById() throws Exception {
        Building building = new Building("Building A");
        when(buildingService.findById(1L)).thenReturn(building);

        mockMvc.perform(get("/api/building/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Building A"));

        verify(buildingService, times(1)).findById(1L);
    }

    @Test
    void testGetBuildingById_NotFound() throws Exception {
        when(buildingService.findById(1L)).thenReturn(null);

        mockMvc.perform(get("/api/building/1"))
                .andExpect(status().isNotFound());

        verify(buildingService, times(1)).findById(1L);
    }

    @Test
    void testAddBuilding() throws Exception {
        Building building = new Building("Building A");
        when(buildingService.save(any(Building.class))).thenReturn(building);

        mockMvc.perform(post("/api/building/add")
                        .param("name", "Building A"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Building A"));

        verify(buildingService, times(1)).save(any(Building.class));
    }

    @Test
    void testDeleteBuilding() throws Exception {
        Building building = new Building("Building A");
        when(buildingService.findById(1L)).thenReturn(building);
        doNothing().when(buildingService).deleteById(1L);

        mockMvc.perform(delete("/api/building/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Building deleted successfully"));

        verify(buildingService, times(1)).findById(1L);
        verify(buildingService, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteBuilding_NotFound() throws Exception {
        when(buildingService.findById(1L)).thenReturn(null);

        mockMvc.perform(delete("/api/building/1"))
                .andExpect(status().isNotFound());

        verify(buildingService, times(1)).findById(1L);
    }

    @Test
    void testGetRoomFromBuilding() throws Exception {
        Building building = new Building("Building A");
        Room room = new Room("Room A", 100, building);
        when(buildingService.findById(1L)).thenReturn(building);
        when(buildingService.findRoomByName("Room A", 1L)).thenReturn(room);

        mockMvc.perform(get("/api/building/1/room")
                        .param("roomName", "Room A"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Room A"));

        verify(buildingService, times(1)).findById(1L);
    }

    @Test
    void testAddRoomToBuilding() throws Exception {
        Building building = new Building("Building A");
        Room room = new Room("Room A", 100, building);
        when(buildingService.findById(1L)).thenReturn(building);
        when(roomService.save(any(Room.class))).thenReturn(room);

        mockMvc.perform(post("/api/building/1/room")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Room A\"}"))
                .andExpect(status().isOk());

        verify(buildingService, times(1)).findById(1L);
        verify(roomService, times(1)).save(any(Room.class));
    }

    @Test
    void testDeleteRoomFromBuilding() throws Exception {
        Building building = new Building("Building A");
        Room room = new Room("Room A", 100, building);
        room.setId(1L);
        when(buildingService.findById(1L)).thenReturn(building);
        when(buildingService.findRoomByName("Room A", 1L)).thenReturn(room);
        doNothing().when(roomService).deleteById(1L);

        mockMvc.perform(delete("/api/building/1/room")
                        .param("roomName", "Room A"))
                .andExpect(status().isOk())
                .andExpect(content().string("Room successfully deleted"));

        verify(buildingService, times(1)).findById(1L);
        verify(roomService, times(1)).deleteById(1L);
    }
}