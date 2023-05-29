package com.example.backend.util;

import com.example.backend.dto.AuditDTO;
import com.example.backend.dto.UserDTO;
import com.example.backend.entities.*;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserUtil {

    public UserDTO toUserDTO(User user) {
        List<Audit> audits = user.getAudits();
        List<AuditDTO> auditDTOs;
        if (audits != null) {
            auditDTOs = audits.stream()
                    .map(audit -> {
                        return new AuditDTO(audit.getId(), audit.getDescription(), audit.getDateTime());
                    })
                    .collect(Collectors.toList());
        } else {
            auditDTOs = Collections.emptyList();
        }

        String userType = getUserType(user);

        UserDTO userDTO = new UserDTO(user.getId(), user.getName(), user.getUsername(), user.getPassword(), auditDTOs, userType);
        return userDTO;
    }

    public String getUserType(User user) {
        if (user instanceof Employee) {
            return "employee";
        } else if (user instanceof Coordinator) {
            return "coordinator";
        } else if (user instanceof DocumentEditor) {
            return "document_editor";
        } else if (user instanceof DocumentApprover) {
            return "document_approver";
        } else {
            return null;
        }
    }

    public User toUser(UserDTO userDTO) {
        User user;

        if (userDTO.getUserType() == null) {
            throw new IllegalArgumentException("O tipo de usuário é necessário para a conversão");
        }

        user = switch (userDTO.getUserType().toLowerCase()) {
            case "employee" -> new Employee();
            case "coordinator" -> new Coordinator();
            case "document_editor" -> new DocumentEditor();
            case "document_approver" -> new DocumentApprover();
            default -> throw new IllegalArgumentException("Invalid user type: " + userDTO.getUserType());
        };

        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setPassword(""); // Defina a senha corretamente, se necessário
        // Atualize outros campos conforme necessário

        return user;
    }

    public void updateUser(User user, UserDTO userDTO) {
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        // Atualize outros campos conforme necessário
    }
}
