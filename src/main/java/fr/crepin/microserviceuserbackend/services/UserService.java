package fr.crepin.microserviceuserbackend.services;

import fr.crepin.microserviceuserbackend.dto.UserRequest;
import fr.crepin.microserviceuserbackend.dto.UserResponse;

public interface UserService {
    UserResponse switchTenantOwner(UserRequest request);
}
