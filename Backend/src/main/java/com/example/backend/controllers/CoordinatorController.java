package com.example.backend.controllers;

import com.example.backend.dto.CoordinatorDTO;
import com.example.backend.services.CoordinatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/coordinators")
public class CoordinatorController {

    @Autowired
    private CoordinatorService coordinatorService;

    @GetMapping
    public ResponseEntity<List<CoordinatorDTO>> getAllCoordinators() {
        return ResponseEntity.ok(coordinatorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoordinatorDTO> getCoordinatorById(@PathVariable Long id) {
        return ResponseEntity.ok(coordinatorService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CoordinatorDTO> createCoordinator(@RequestBody CoordinatorDTO coordinatorDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(coordinatorService.save(coordinatorDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CoordinatorDTO> updateCoordinator(@PathVariable Long id, @RequestBody CoordinatorDTO coordinatorDTO) {
        return ResponseEntity.ok(coordinatorService.update(id, coordinatorDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoordinator(@PathVariable Long id) {
        coordinatorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
