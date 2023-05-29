package com.example.backend.controllers;

import com.example.backend.dto.DocumentEditorDTO;
import com.example.backend.services.DocumentEditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/document-editors")
public class DocumentEditorController {

    @Autowired
    private DocumentEditorService documentEditorService;

    @GetMapping
    public ResponseEntity<List<DocumentEditorDTO>> getAllDocumentEditors() {
        return ResponseEntity.ok(documentEditorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentEditorDTO> getDocumentEditorById(@PathVariable Long id) {
        return ResponseEntity.ok(documentEditorService.findById(id));
    }

    @PostMapping
    public ResponseEntity<DocumentEditorDTO> createDocumentEditor(@RequestBody DocumentEditorDTO documentEditorDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(documentEditorService.save(documentEditorDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentEditorDTO> updateDocumentEditor(@PathVariable Long id, @RequestBody DocumentEditorDTO documentEditorDTO) {
        return ResponseEntity.ok(documentEditorService.update(id, documentEditorDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocumentEditor(@PathVariable Long id) {
        documentEditorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
