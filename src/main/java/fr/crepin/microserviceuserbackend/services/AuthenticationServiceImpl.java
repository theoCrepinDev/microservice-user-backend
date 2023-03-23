package fr.crepin.microserviceuserbackend.services;

import fr.crepin.microserviceuserbackend.config.JwtService;
import fr.crepin.microserviceuserbackend.dao.entity.UserData;
import fr.crepin.microserviceuserbackend.dao.entity.UserRole;
import fr.crepin.microserviceuserbackend.dao.repository.UserDataRepository;
import fr.crepin.microserviceuserbackend.dto.AuthenticationRequest;
import fr.crepin.microserviceuserbackend.dto.AuthenticationResponse;
import fr.crepin.microserviceuserbackend.dto.RegisterRequest;
import fr.crepin.microserviceuserbackend.exception.RegisterUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserDataRepository repository;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationServiceImpl(
            UserDataRepository repository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuthenticationManager authenticationManager
    ) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        } catch (Exception e) {
            throw new UsernameNotFoundException("Invalid username or password");
        }

        UserData user = null;
        try {
            user = repository.findByEmailOrUsername(request.getUsername());
        } catch (Exception e) {
            throw e;
        }
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).role(user.getRole().getRole().toUpperCase()).isValid(true).message("Authentification réussie !").build();
    }

    public AuthenticationResponse register(RegisterRequest request) {
        if (isUserExist(request.getUsername(), request.getEmail())) {
            throw new RegisterUserException("User with this email or username already exist");
        }
        var user = new UserData(request.getUsername(), passwordEncoder.encode(request.getPassword()), request.getEmail(), new UserRole("USER"));
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    private boolean isUserExist(String username, String email) {
        var user = repository.findByEmailOrUsername(username, email);
        return user != null;
    }
}
