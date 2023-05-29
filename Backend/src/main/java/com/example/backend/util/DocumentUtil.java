package com.example.backend.util;

import com.example.backend.entities.Employee;
import com.example.backend.dto.DocumentDTO;
import com.example.backend.entities.Document;

import java.util.List;
import java.util.stream.Collectors;

public class DocumentUtil {

    public static DocumentDTO toDocumentDTO(Document document) {
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setId(document.getId());
        documentDTO.setTitle(document.getTitle());
        documentDTO.setAcronym(document.getAcronym());
        documentDTO.setDocumentNumber(document.getDocumentNumber());
        documentDTO.setRevision(document.getRevision());
        documentDTO.setStatus(document.getStatus());
        documentDTO.setType(document.getType());
        documentDTO.setPdfFile(document.getPdfFile());

        List<Long> employeeIds = document.getEmployees().stream()
                .map(Employee::getId)
                .collect(Collectors.toList());
        documentDTO.setEmployeeIds(employeeIds);

        return documentDTO;
    }

    public static Document toDocument(DocumentDTO documentDTO, List<Employee> employees) {
        Document document = new Document();
        document.setId(documentDTO.getId());
        document.setTitle(documentDTO.getTitle());
        document.setAcronym(documentDTO.getAcronym());
        document.setDocumentNumber(documentDTO.getDocumentNumber());
        document.setRevision(documentDTO.getRevision());
        document.setStatus(documentDTO.getStatus());
        document.setType(documentDTO.getType());
        document.setPdfFile(documentDTO.getPdfFile());
        document.setEmployees(employees);

        return document;
    }
}
