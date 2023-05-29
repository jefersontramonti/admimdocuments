package com.example.backend.dto;

import com.example.backend.entities.DocumentStatus;

import java.util.List;

public class DocumentDTO {
    private Long id;
    private String title;
    private String acronym;
    private String documentNumber;
    private int revision;
    private DocumentStatus status;
    private String type;
    private byte[] pdfFile;
    private List<Long> employeeIds;

    public DocumentDTO() {
    }

    public DocumentDTO(Long id, String title, String acronym, String documentNumber, int revision, DocumentStatus status, String type, byte[] pdfFile, List<Long> employeeIds) {
        this.id = id;
        this.title = title;
        this.acronym = acronym;
        this.documentNumber = documentNumber;
        this.revision = revision;
        this.status = status;
        this.type = type;
        this.pdfFile = pdfFile;
        this.employeeIds = employeeIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public int getRevision() {
        return revision;
    }

    public void setRevision(int revision) {
        this.revision = revision;
    }

    public DocumentStatus getStatus() {
        return status;
    }

    public void setStatus(DocumentStatus status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getPdfFile() {
        return pdfFile;
    }

    public void setPdfFile(byte[] pdfFile) {
        this.pdfFile = pdfFile;
    }

    public List<Long> getEmployeeIds() {
        return employeeIds;
    }

    public void setEmployeeIds(List<Long> employeeIds) {
        this.employeeIds = employeeIds;
    }


}
