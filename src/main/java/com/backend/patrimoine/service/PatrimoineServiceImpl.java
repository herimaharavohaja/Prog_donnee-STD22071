package com.backend.patrimoine.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fresh.coding.patrimoineapi.model.Patrimoine;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PatrimoineServiceImpl implements com.backend.patrimoine.service.PatrimoineService {

    private static final String JSON_FILE_PATH = "src/main/resources/patrimoines.json";
    private final List<Patrimoine> patrimoines = new ArrayList<>();
    private final ObjectMapper objectMapper;

    @Autowired
    public PatrimoineServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        loadPatrimoines();
    }

    @SneakyThrows
    private void loadPatrimoines() {
        File jsonFile = new File(JSON_FILE_PATH);
        if (jsonFile.exists()) {
            Patrimoine[] existingPatrimoines = objectMapper.readValue(jsonFile, Patrimoine[].class);
            patrimoines.addAll(Arrays.asList(existingPatrimoines));
        }
    }

    @Override
    public Patrimoine create(Patrimoine patrimoine) {
        patrimoine.setDerniereModification(LocalDateTime.now());
        patrimoines.add(patrimoine);
        savePatrimoines();
        return patrimoine;
    }

    @Override
    public Patrimoine update(String name, Patrimoine patrimoine) {
        Patrimoine existingPatrimoine = findByName(name);
        if (existingPatrimoine != null) {
            existingPatrimoine.setDerniereModification(LocalDateTime.now());
            existingPatrimoine.setPossesseur(patrimoine.getPossesseur());
        } else {
            patrimoine.setPossesseur(name);
            patrimoine.setDerniereModification(LocalDateTime.now());
            patrimoines.add(patrimoine);
        }
        savePatrimoines();
        return patrimoine;
    }

    @SneakyThrows
    private void savePatrimoines() {
        objectMapper.writeValue(new File(JSON_FILE_PATH), patrimoines);
    }

    @Override
    public Patrimoine findByName(String name) {
        return patrimoines.stream()
                .filter(p -> p.getPossesseur().equals(name))
                .findFirst()
                .orElse(null);
    }
}
