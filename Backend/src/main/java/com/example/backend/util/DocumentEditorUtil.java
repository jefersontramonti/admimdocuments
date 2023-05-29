package com.example.backend.util;

import com.example.backend.dto.DocumentEditorDTO;
import com.example.backend.entities.DocumentEditor;

public class DocumentEditorUtil {

    public DocumentEditorDTO toDocumentEditorDTO(DocumentEditor documentEditor) {
        DocumentEditorDTO documentEditorDTO = new DocumentEditorDTO();
        documentEditorDTO.setId(documentEditor.getId());
        documentEditorDTO.setName(documentEditor.getName());
        documentEditorDTO.setUsername(documentEditor.getUsername());
        documentEditorDTO.setPassword(documentEditor.getPassword());

        return documentEditorDTO;
    }

    public static DocumentEditor toDocumentEditor(DocumentEditorDTO documentEditorDTO) {
        DocumentEditor documentEditor = new DocumentEditor();
        documentEditor.setId(documentEditorDTO.getId());
        documentEditor.setName(documentEditorDTO.getName());
        documentEditor.setUsername(documentEditorDTO.getUsername());
        documentEditor.setPassword(documentEditorDTO.getPassword());

        return documentEditor;
    }

    public static void updateDocumentEditor(DocumentEditor documentEditor, DocumentEditorDTO documentEditorDTO) {
        documentEditor.setName(documentEditorDTO.getName());
        documentEditor.setUsername(documentEditorDTO.getUsername());
        documentEditor.setPassword(documentEditorDTO.getPassword());
    }
}
