package fr.crepin.microserviceuserbackend.controller;

import fr.crepin.microserviceuserbackend.dto.AuthenticationRequest;
import fr.crepin.microserviceuserbackend.dto.AuthenticationResponse;
import fr.crepin.microserviceuserbackend.dto.RegisterRequest;
import fr.crepin.microserviceuserbackend.exception.RegisterUserException;
import fr.crepin.microserviceuserbackend.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "${front.url}")
public class AuthenticationController {

    private final AuthenticationService service;

    @Autowired
    public AuthenticationController(
            AuthenticationService service
    ) {
        this.service = service;
    }

    /**
     * @PostMapping("/register") public ResponseEntity<ImmutableAuthenticationResponse> register(
     * @RequestBody ImmutableRegisterRequest request
     * ) {
     * return ResponseEntity.ok(service.register(request));
     * }
     */

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        try {
            return ResponseEntity.ok(service.register(request));
        } catch (RegisterUserException e) {
            return ResponseEntity.status(400).body(AuthenticationResponse.builder().isValid(false).message("Un utilisateur exist déjà avec cet email ou ce nom d'utilisateur.").build());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest request
    ) {
        try {
            return ResponseEntity.ok(service.login(request));

        } catch (Exception e) {
            return ResponseEntity.status(403).body(AuthenticationResponse.builder().isValid(false).message("Informations de connexion incorrectes").build());
        }
    }

}
