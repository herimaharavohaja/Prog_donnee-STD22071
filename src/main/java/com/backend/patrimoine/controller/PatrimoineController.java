package com.backend.patrimoine.controller;
import com.backend.patrimoine.model.Patrimoine;
import com.backend.patrimoine.service.PatrimoineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patrimoines")
public class PatrimoineController {

    private final PatrimoineService patrimoineService;

    public PatrimoineController(PatrimoineService patrimoineService) {
        this.patrimoineService = patrimoineService;
    }

    @PutMapping
    public ResponseEntity<Patrimoine> savePatrimoine(@RequestBody Patrimoine patrimoine) {
        Patrimoine createdPatrimoine = patrimoineService.create(patrimoine);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPatrimoine);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patrimoine> updatePatrimoine(
            @PathVariable(name = "id") String name,
            @RequestBody Patrimoine patrimoine
    ) {
        Patrimoine updatedPatrimoine = patrimoineService.update(name, patrimoine);
        return ResponseEntity.ok(updatedPatrimoine);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patrimoine> findPatrimoineByName(@PathVariable(name = "id") String name) {
        Patrimoine patrimoine = patrimoineService.findByName(name);
        if (patrimoine != null) {
            return ResponseEntity.ok(patrimoine);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}