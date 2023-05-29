package com.example.backend.controllers;

import com.example.backend.dto.EmployeeDTO;
import com.example.backend.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.save(employeeDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, employeeDTO));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")//buscar todos os funcionários (com filtragem opcional por nome)
    public ResponseEntity<List<EmployeeDTO>> searchEmployeesByName(@RequestParam(value = "name", required = false) String name) {
        List<EmployeeDTO> employees = employeeService.searchEmployeesByName(name);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PutMapping("/{id}/documents")// atualizar os documentos associados a um funcionário,
    public ResponseEntity<EmployeeDTO> updateEmployeeDocuments(@PathVariable Long id, @RequestBody List<Long> documentIds) {
        EmployeeDTO updatedEmployee = employeeService.updateEmployeeDocuments(id, documentIds);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @GetMapping("/{id}/documents")//retorna os detalhes de um funcionário específico, incluindo os documentos associados a ele
    public ResponseEntity<EmployeeDTO> getEmployeeWithDocuments(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.findByIdWithDocuments(id));
    }


}
