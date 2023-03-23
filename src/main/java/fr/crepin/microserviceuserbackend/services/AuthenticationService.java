package fr.crepin.microserviceuserbackend.services;

import fr.crepin.microserviceuserbackend.dto.AuthenticationRequest;
import fr.crepin.microserviceuserbackend.dto.AuthenticationResponse;
import fr.crepin.microserviceuserbackend.dto.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse login(AuthenticationRequest request);
}
