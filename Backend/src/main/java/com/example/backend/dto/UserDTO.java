package com.example.backend.dto;


import java.util.List;

public class UserDTO {
    private Long id;
    private String name;
    private String password;
    private String username;
    private List<AuditDTO> audits;

    private String userType;

    public UserDTO() {
    }

    public UserDTO(Long id, String name, String username, String password, List<AuditDTO> audits, String userType) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.audits = audits;
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<AuditDTO> getAudits() {
        return audits;
    }

    public void setAudits(List<AuditDTO> audits) {
        this.audits = audits;
    }
}
