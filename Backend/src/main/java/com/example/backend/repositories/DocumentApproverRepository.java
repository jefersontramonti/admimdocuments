package com.example.backend.repositories;

import com.example.backend.entities.DocumentApprover;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentApproverRepository extends JpaRepository<DocumentApprover, Long> {
}
