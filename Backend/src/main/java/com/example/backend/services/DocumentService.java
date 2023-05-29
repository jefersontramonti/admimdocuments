package com.example.backend.services;

import com.example.backend.dto.DocumentDTO;
import com.example.backend.entities.Document;
import com.example.backend.entities.Employee;
import com.example.backend.repositories.DocumentRepository;
import com.example.backend.repositories.EmployeeRepository;
import com.example.backend.util.DocumentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<DocumentDTO> findAll() {
        return documentRepository.findAll().stream()
                .map(DocumentUtil::toDocumentDTO)
                .collect(Collectors.toList());
    }

    public DocumentDTO findById(Long id) {
        return documentRepository.findById(id)
                .map(DocumentUtil::toDocumentDTO)
                .orElseThrow(() -> new RuntimeException("Document not found"));
    }

    public DocumentDTO save(DocumentDTO documentDTO) {
        List<Employee> employees = employeeRepository.findAllById(documentDTO.getEmployeeIds());
        Document document = DocumentUtil.toDocument(documentDTO, employees);
        document = documentRepository.save(document);
        return DocumentUtil.toDocumentDTO(document);
    }

    public DocumentDTO update(Long id, DocumentDTO documentDTO) {
        documentDTO.setId(id);
        return save(documentDTO);
    }

    public void delete(Long id) {
        documentRepository.deleteById(id);
    }

    public List<DocumentDTO> searchDocuments(String title, String acronym) {
        return documentRepository.findByTitleContainingIgnoreCase(title).stream()
                .filter(document -> acronym == null || document.getAcronym().equalsIgnoreCase(acronym))
                .map(DocumentUtil::toDocumentDTO)
                .collect(Collectors.toList());
    }

    public DocumentDTO addEmployeeToDocument(Long documentId, Long employeeId) {
        Document document = documentRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Document not found"));

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        document.getEmployees().add(employee);
        document = documentRepository.save(document);

        return DocumentUtil.toDocumentDTO(document);
    }

}
