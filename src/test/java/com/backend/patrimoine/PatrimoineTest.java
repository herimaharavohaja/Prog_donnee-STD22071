package com.backend.patrimoine;

import com.backend.patrimoine.model.Patrimoine;
import com.backend.patrimoine.service.PatrimoineServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class PatrimoineServiceImplTest {

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private PatrimoineServiceImpl patrimoineService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        patrimoineService = new PatrimoineServiceImpl("", objectMapper);
    }

    @Test
    void shouldReturnExistingPatrimoine_WhenFoundByName() {
        String name = "John Doe";
        Patrimoine patrimoine = new Patrimoine(name, LocalDateTime.now());
        patrimoineService.create(patrimoine);

        Patrimoine foundPatrimoine = patrimoineService.findByName(name);

        assertNotNull(foundPatrimoine);
        assertEquals(name, foundPatrimoine.getPossesseur());
    }

    @Test
    void shouldReturnNull_WhenPatrimoineDoesNotExist() {
        String name = "Non Existent";

        Patrimoine foundPatrimoine = patrimoineService.findByName(name);

        assertNull(foundPatrimoine);
    }

    @Test
    void shouldAddNewPatrimoine_WhenSaved() throws Exception {
        String name = "John Doe";
        when(objectMapper.readValue(any(File.class), eq(Patrimoine[].class))).thenReturn(new Patrimoine[0]);
        doNothing().when(objectMapper).writeValue(any(File.class), any());

        Patrimoine savedPatrimoine = patrimoineService.create(new Patrimoine(name, LocalDateTime.now()));;

        assertNotNull(savedPatrimoine);
        assertEquals(name, savedPatrimoine.getPossesseur());
        verify(objectMapper, times(1)).writeValue(any(File.class), any());
    }

    @Test
    void shouldUpdateAndPersist_WhenPatrimoineAlreadyExists() throws Exception {
        String name = "John Doe";
        Patrimoine existingPatrimoine = new Patrimoine(name, LocalDateTime.now().minusDays(1));
        when(objectMapper.readValue(any(File.class), eq(Patrimoine[].class))).thenReturn(new Patrimoine[]{existingPatrimoine});
        doNothing().when(objectMapper).writeValue(any(File.class), any());

        Patrimoine updatedPatrimoine = patrimoineService.create(new Patrimoine(name, LocalDateTime.now()));

        assertNotNull(updatedPatrimoine);
        assertEquals(name, updatedPatrimoine.getPossesseur());
        assertNotEquals(existingPatrimoine.getDerniereModification(), updatedPatrimoine.getDerniereModification());
        verify(objectMapper, times(1)).writeValue(any(File.class), any());
    }
}