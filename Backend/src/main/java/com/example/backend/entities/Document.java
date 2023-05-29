package com.example.backend.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;


@Entity
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String acronym;
    private String documentNumber;
    private int revision;

    @Enumerated(EnumType.STRING)
    private DocumentStatus status;

    private String type;
    @Lob
    private byte[] pdfFile;

    @ManyToMany(mappedBy = "documents")
    private List<Employee> employees;

    public Document() {
    }

    public Document(Long id, String title, String acronym, String documentNumber, int revision, DocumentStatus status, String type, byte[] pdfFile, List<Employee> employees) {
        this.id = id;
        this.title = title;
        this.acronym = acronym;
        this.documentNumber = documentNumber;
        this.revision = revision;
        this.status = status;
        this.type = type;
        this.pdfFile = pdfFile;
        this.employees = employees;
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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Document document)) return false;
        return Objects.equals(getId(), document.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
