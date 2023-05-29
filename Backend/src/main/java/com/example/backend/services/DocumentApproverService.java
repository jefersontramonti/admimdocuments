package com.example.backend.services;

import com.example.backend.dto.DocumentApproverDTO;
import com.example.backend.entities.DocumentApprover;
import com.example.backend.repositories.DocumentApproverRepository;
import com.example.backend.util.DocumentApproverUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentApproverService {

    @Autowired
    private DocumentApproverRepository documentApproverRepository;

    private DocumentApproverUtil documentApproverUtil;

    public List<DocumentApproverDTO> findAll() {
        return documentApproverRepository.findAll().stream()
                .map(DocumentApproverUtil::toDocumentApproverDTO)
                .collect(Collectors.toList());
    }

    public DocumentApproverDTO findById(Long id) {
        return documentApproverRepository.findById(id)
                .map(DocumentApproverUtil::toDocumentApproverDTO)
                .orElseThrow(() -> new RuntimeException("DocumentApprover not found"));
    }

    public DocumentApproverDTO save(DocumentApproverDTO documentApproverDTO) {
        DocumentApprover documentApprover = DocumentApproverUtil.toDocumentApprover(documentApproverDTO);
        documentApprover = documentApproverRepository.save(documentApprover);
        return DocumentApproverUtil.toDocumentApproverDTO(documentApprover);
    }

    public DocumentApproverDTO update(Long id, DocumentApproverDTO documentApproverDTO) {
        DocumentApprover documentApprover = documentApproverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DocumentApprover not found"));
        DocumentApproverUtil.updateDocumentApprover(documentApprover, documentApproverDTO);
        DocumentApprover updatedDocumentApprover = documentApproverRepository.save(documentApprover);
        return DocumentApproverUtil.toDocumentApproverDTO(updatedDocumentApprover);
    }

    public void delete(Long id) {
        documentApproverRepository.deleteById(id);
    }
}
