package com.example.backend.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Employee extends User {
    @ManyToMany
    @JoinTable(
            name = "employee_document",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "document_id"))
    private List<Document> documents;

    public Employee() {
        this.documents = new ArrayList<>();
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public Employee(List<Document> documents) {
        this.documents = documents;
    }

    public Employee(Long id, String name, String username, String password, List<Audit> audits, List<Document> documents) {
        super(id, name, username, password, audits);
        this.documents = documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

}
