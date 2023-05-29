package com.example.backend.repositories;

import com.example.backend.entities.DocumentEditor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentEditorRepository extends JpaRepository<DocumentEditor, Long> {
}
