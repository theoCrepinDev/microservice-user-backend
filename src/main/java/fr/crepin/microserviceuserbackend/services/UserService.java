package fr.crepin.microserviceuserbackend.services;

import fr.crepin.microserviceuserbackend.dto.UserRequest;
import fr.crepin.microserviceuserbackend.dto.UserResponse;
import fr.crepin.microserviceuserbackend.exception.DataValidationException;

public interface UserService {
    UserResponse switchTenantOwner(UserRequest request) throws DataValidationException;
}
