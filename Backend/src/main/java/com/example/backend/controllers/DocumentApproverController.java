package com.example.backend.controllers;

import com.example.backend.dto.DocumentApproverDTO;
import com.example.backend.services.DocumentApproverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/document-approvers")
public class DocumentApproverController {

    @Autowired
    private DocumentApproverService documentApproverService;

    @GetMapping
    public ResponseEntity<List<DocumentApproverDTO>> getAllDocumentApprovers() {
        return ResponseEntity.ok(documentApproverService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentApproverDTO> getDocumentApproverById(@PathVariable Long id) {
        return ResponseEntity.ok(documentApproverService.findById(id));
    }

    @PostMapping
    public ResponseEntity<DocumentApproverDTO> createDocumentApprover(@RequestBody DocumentApproverDTO documentApproverDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(documentApproverService.save(documentApproverDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentApproverDTO> updateDocumentApprover(@PathVariable Long id, @RequestBody DocumentApproverDTO documentApproverDTO) {
        return ResponseEntity.ok(documentApproverService.update(id, documentApproverDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocumentApprover(@PathVariable Long id) {
        documentApproverService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
