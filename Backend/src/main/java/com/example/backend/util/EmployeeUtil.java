package com.example.backend.util;

import com.example.backend.dto.DocumentDTO;
import com.example.backend.dto.EmployeeDTO;
import com.example.backend.entities.Document;
import com.example.backend.entities.Employee;
import com.example.backend.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeUtil {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private UserUtil userUtil;

    public EmployeeDTO toEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setUsername(employee.getUsername());
        employeeDTO.setUserType(userUtil.getUserType(employee));

        List<Long> documentIds = employee.getDocuments().stream()
                .map(Document::getId)
                .collect(Collectors.toList());
        employeeDTO.setDocumentIds(documentIds);

        return employeeDTO;
    }

    public EmployeeDTO toEmployeeDTOWithDocuments(Employee employee) {
        EmployeeDTO employeeDTO = toEmployeeDTO(employee);

        List<DocumentDTO> documents = employee.getDocuments().stream()
                .map(DocumentUtil::toDocumentDTO)
                .collect(Collectors.toList());
        employeeDTO.setDocuments(documents);

        return employeeDTO;
    }

    public void updateEmployee(Employee employee, EmployeeDTO employeeDTO) {
        employee.setName(employeeDTO.getName());
        employee.setUsername(employeeDTO.getUsername());
    }

    public Employee updateEmployeeDocuments(Employee employee, List<Long> documentIds) {
        List<Document> documents = documentRepository.findAllById(documentIds);
        employee.setDocuments(documents);
        return employee;
    }

    public Employee toEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setName(employeeDTO.getName());
        employee.setUsername(employeeDTO.getUsername());

        List<Long> documentIds = employeeDTO.getDocumentIds();
        if (documentIds != null && !documentIds.isEmpty()) {
            List<Document> documents = documentRepository.findAllById(documentIds);
            employee.setDocuments(documents);
        }

        return employee;
    }
}
