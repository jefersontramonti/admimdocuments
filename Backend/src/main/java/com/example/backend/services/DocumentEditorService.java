package com.example.backend.services;

import com.example.backend.dto.DocumentEditorDTO;
import com.example.backend.entities.DocumentEditor;
import com.example.backend.repositories.DocumentEditorRepository;
import com.example.backend.util.DocumentEditorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentEditorService {

    @Autowired
    private DocumentEditorRepository documentEditorRepository;

    private DocumentEditorUtil documentEditorUtil;

    //Buscar todos os editores de documentos
    public List<DocumentEditorDTO> findAll() {
        return documentEditorRepository.findAll().stream()
                .map(documentEditorUtil::toDocumentEditorDTO)
                .collect(Collectors.toList());
    }

    //Buscar um editor de documento por ID
    public DocumentEditorDTO findById(Long id) {
        return documentEditorRepository.findById(id)
                .map(documentEditorUtil::toDocumentEditorDTO)
                .orElseThrow(() -> new RuntimeException("DocumentEditor not found"));
    }

    //Salvar um novo editor de documento
    public DocumentEditorDTO save(DocumentEditorDTO documentEditorDTO) {
        DocumentEditor documentEditor = DocumentEditorUtil.toDocumentEditor(documentEditorDTO);
        documentEditor = documentEditorRepository.save(documentEditor);
        return documentEditorUtil.toDocumentEditorDTO(documentEditor);
    }

    //Atualizar um editor de documento existente
    public DocumentEditorDTO update(Long id, DocumentEditorDTO documentEditorDTO) {
        DocumentEditor documentEditor = documentEditorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DocumentEditor not found"));
        DocumentEditorUtil.updateDocumentEditor(documentEditor, documentEditorDTO);
        DocumentEditor updatedDocumentEditor = documentEditorRepository.save(documentEditor);
        return documentEditorUtil.toDocumentEditorDTO(updatedDocumentEditor);
    }

    //Excluir um editor de documento
    public void delete(Long id) {
        documentEditorRepository.deleteById(id);
    }
}
