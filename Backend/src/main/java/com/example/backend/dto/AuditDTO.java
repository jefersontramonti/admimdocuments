package com.example.backend.dto;

import java.time.LocalDateTime;

public class AuditDTO {
    private Long id;
    private String description;
    private LocalDateTime dateTime;
    private UserDTO user;

    public AuditDTO() {
    }

    public AuditDTO(Long id, String description, LocalDateTime dateTime) {
        this.id = id;
        this.description = description;
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

}
