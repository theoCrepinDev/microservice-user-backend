package fr.crepin.microserviceuserbackend.services;

import fr.crepin.microserviceuserbackend.dao.entity.UserData;
import fr.crepin.microserviceuserbackend.dto.UserRequest;
import fr.crepin.microserviceuserbackend.dto.UserResponse;
import fr.crepin.microserviceuserbackend.exception.DataValidationException;

import java.util.List;

public interface UserService {
    UserResponse switchTenantOwner(UserRequest request) throws DataValidationException;

    List<UserData> findAllUsers();
}
