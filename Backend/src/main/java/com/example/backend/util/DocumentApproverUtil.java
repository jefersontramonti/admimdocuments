package com.example.backend.util;

import com.example.backend.dto.DocumentApproverDTO;
import com.example.backend.entities.DocumentApprover;

public class DocumentApproverUtil {

    public static DocumentApproverDTO toDocumentApproverDTO(DocumentApprover documentApprover) {
        DocumentApproverDTO documentApproverDTO = new DocumentApproverDTO();
        documentApproverDTO.setId(documentApprover.getId());
        documentApproverDTO.setName(documentApprover.getName());
        documentApproverDTO.setUsername(documentApprover.getUsername());
        documentApproverDTO.setPassword(documentApprover.getPassword());

        return documentApproverDTO;
    }

    public static DocumentApprover toDocumentApprover(DocumentApproverDTO documentApproverDTO) {
        DocumentApprover documentApprover = new DocumentApprover();
        documentApprover.setId(documentApproverDTO.getId());
        documentApprover.setName(documentApproverDTO.getName());
        documentApprover.setUsername(documentApproverDTO.getUsername());
        documentApprover.setPassword(documentApproverDTO.getPassword());

        return documentApprover;
    }

    public static void updateDocumentApprover(DocumentApprover documentApprover, DocumentApproverDTO documentApproverDTO) {
        documentApprover.setName(documentApproverDTO.getName());
        documentApprover.setUsername(documentApproverDTO.getUsername());
        documentApprover.setPassword(documentApproverDTO.getPassword());
    }
}
