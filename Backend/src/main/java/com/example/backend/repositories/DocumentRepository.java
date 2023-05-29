package com.example.backend.repositories;

import com.example.backend.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByTitleContainingIgnoreCase(String title); // Método para buscar documentos pelo título (filtragem)
    // Adicione mais métodos aqui para filtragem adicional, se necessário
}
