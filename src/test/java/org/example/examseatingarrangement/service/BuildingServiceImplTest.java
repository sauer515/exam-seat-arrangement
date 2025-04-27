package org.example.examseatingarrangement.service;

import org.example.examseatingarrangement.model.Building;
import org.example.examseatingarrangement.repository.BuildingRepository;
import org.example.examseatingarrangement.service.impl.BuildingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuildingServiceImplTest {

    @Mock
    private BuildingRepository buildingRepository;

    @InjectMocks
    private BuildingServiceImpl buildingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<Building> buildings = Arrays.asList(new Building("Building A"), new Building("Building B"));
        when(buildingRepository.findAll()).thenReturn(buildings);

        List<Building> result = buildingService.findAll();

        assertEquals(2, result.size());
        verify(buildingRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Building building = new Building("Building A");
        when(buildingRepository.findById(1L)).thenReturn(Optional.of(building));

        Building result = buildingService.findById(1L);

        assertNotNull(result);
        assertEquals("Building A", result.getName());
        verify(buildingRepository, times(1)).findById(1L);
    }

    @Test
    void testSave() {
        Building building = new Building("Building A");
        when(buildingRepository.save(building)).thenReturn(building);

        Building result = buildingService.save(building);

        assertNotNull(result);
        assertEquals("Building A", result.getName());
        verify(buildingRepository, times(1)).save(building);
    }

    @Test
    void testDeleteById() {
        doNothing().when(buildingRepository).deleteById(1L);

        buildingService.deleteById(1L);

        verify(buildingRepository, times(1)).deleteById(1L);
    }
}