package com.example.backend.services;

import com.example.backend.dto.EmployeeDTO;
import com.example.backend.entities.Employee;
import com.example.backend.repositories.DocumentRepository;
import com.example.backend.repositories.EmployeeRepository;
import com.example.backend.util.EmployeeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeUtil employeeUtil;

    @Autowired
    private DocumentRepository documentRepository;

    public List<EmployeeDTO> findAll() {
        return employeeRepository.findAll().stream()
                .map(employeeUtil::toEmployeeDTO)
                .collect(Collectors.toList());
    }

    public EmployeeDTO findById(Long id) {
        return employeeRepository.findById(id)
                .map(employeeUtil::toEmployeeDTO)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        Employee employee = employeeUtil.toEmployee(employeeDTO);
        employee = employeeRepository.save(employee);
        return employeeUtil.toEmployeeDTO(employee);
    }

    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    public List<EmployeeDTO> searchEmployeesByName(String name) {
        return employeeRepository.findByNameContainingIgnoreCase(name).stream()
                .map(employeeUtil::toEmployeeDTO)
                .collect(Collectors.toList());
    }

    public EmployeeDTO updateEmployeeDocuments(Long id, List<Long> documentIds) {
        Employee employee = employeeRepository.findByIdWithDocuments(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        Employee updatedEmployee = employeeUtil.updateEmployeeDocuments(employee, documentIds);
        employeeRepository.save(updatedEmployee);
        return employeeUtil.toEmployeeDTO(updatedEmployee);
    }

    public EmployeeDTO findByIdWithDocuments(Long id) {
        Employee employee = employeeRepository.findByIdWithDocuments(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return employeeUtil.toEmployeeDTOWithDocuments(employee);
    }

    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        employeeUtil.updateEmployee(employee, employeeDTO);
        Employee updatedEmployee = employeeRepository.save(employee);
        return employeeUtil.toEmployeeDTO(updatedEmployee);
    }

}
