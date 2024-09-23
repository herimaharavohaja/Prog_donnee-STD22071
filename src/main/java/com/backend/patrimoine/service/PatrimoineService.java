package com.backend.patrimoine.service;
import com.fresh.coding.patrimoineapi.model.Patrimoine;

public interface PatrimoineService {
    Patrimoine create(Patrimoine patrimoine);
    Patrimoine update(String name, Patrimoine patrimoine);
    Patrimoine findByName(String name);
}
