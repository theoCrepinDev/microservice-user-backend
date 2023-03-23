package fr.crepin.microserviceuserbackend.dao.repository;

import fr.crepin.microserviceuserbackend.dao.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UserDataRepository extends JpaRepository<UserData, UUID> {
    @Query("SELECT ud FROM UserData ud WHERE ud.email = :usernameOrEmail OR ud.username = :usernameOrEmail")
    UserData findByEmailOrUsername(@Param("usernameOrEmail") String usernameOrEmail);

    @Query("SELECT ud FROM UserData ud WHERE ud.email = :email OR ud.username = :username")
    UserData findByEmailOrUsername(@Param("username") String username, @Param("email") String email);
}
