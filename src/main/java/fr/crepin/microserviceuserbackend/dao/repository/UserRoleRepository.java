package fr.crepin.microserviceuserbackend.dao.repository;

import fr.crepin.microserviceuserbackend.dao.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRoleRepository extends JpaRepository<UserRole, UUID> {
}
