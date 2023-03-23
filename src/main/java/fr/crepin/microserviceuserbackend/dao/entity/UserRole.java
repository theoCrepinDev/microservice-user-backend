package fr.crepin.microserviceuserbackend.dao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(schema = "microservice", name = "user_role")
public class UserRole {
    @Id
    @GeneratedValue
    private UUID id;
    private String role;

    public UserRole(String role) {
        this.role = role;
    }

    public UserRole() {
    }

    public UserRole(UUID id, String role) {
        this.id = id;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
