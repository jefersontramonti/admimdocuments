package com.example.backend.dto;

import java.util.List;

public class EmployeeDTO extends UserDTO {
    private List<Long> documentIds;

    public EmployeeDTO() {
        super();
    }

    public EmployeeDTO(Long id, String name, String username, String password, String userType, List<Long> documentIds, List<DocumentDTO> documents) {
        super(id, name, username, password, null, userType);
        this.documentIds = documentIds;
    }


    public List<Long> getDocumentIds() {
        return documentIds;
    }

    public void setDocumentIds(List<Long> documentIds) {
        this.documentIds = documentIds;
    }

    public void setDocuments(List<DocumentDTO> documents) {
    }
}
