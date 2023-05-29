package com.example.backend.controllers;

import com.example.backend.dto.DocumentDTO;
import com.example.backend.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @GetMapping
    @Transactional
    public ResponseEntity<List<DocumentDTO>> getAllDocuments() {
        return ResponseEntity.ok(documentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentDTO> getDocumentById(@PathVariable Long id) {
        return ResponseEntity.ok(documentService.findById(id));
    }

    @PostMapping
    public ResponseEntity<DocumentDTO> createDocument(@RequestBody DocumentDTO documentDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(documentService.save(documentDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentDTO> updateDocument(@PathVariable Long id, @RequestBody DocumentDTO documentDTO) {
        return ResponseEntity.ok(documentService.update(id, documentDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long id) {
        documentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")// busca todos os documentos disponíveis (com filtragem opcional por título, sigla, etc.)
    public ResponseEntity<List<DocumentDTO>> searchDocuments(@RequestParam(value = "title", required = false) String title,
                                                             @RequestParam(value = "acronym", required = false) String acronym) {
        List<DocumentDTO> documents = documentService.searchDocuments(title, acronym);
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }

    @PostMapping("/create-with-pdf")
    public ResponseEntity<DocumentDTO> createDocumentWithPdf(@RequestPart("document") DocumentDTO documentDTO,
                                                             @RequestPart("pdfFile") MultipartFile pdfFile) throws IOException {
        byte[] pdfBytes = pdfFile.getBytes();
        documentDTO.setPdfFile(pdfBytes);

        if (documentDTO.getEmployeeIds() == null) {
            documentDTO.setEmployeeIds(new ArrayList<>()); // Inicializa employeeIds com uma lista vazia se estiver nulo
        }

        DocumentDTO savedDocument = documentService.save(documentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDocument);
    }

    @PostMapping("/{documentId}/employees/{employeeId}")
    public ResponseEntity<DocumentDTO> addEmployeeToDocument(@PathVariable Long documentId, @PathVariable Long employeeId) {
        DocumentDTO updatedDocumentDTO = documentService.addEmployeeToDocument(documentId, employeeId);
        return ResponseEntity.ok(updatedDocumentDTO);
    }


}
