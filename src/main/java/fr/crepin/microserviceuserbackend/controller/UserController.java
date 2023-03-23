package fr.crepin.microserviceuserbackend.controller;

import fr.crepin.microserviceuserbackend.dto.UserRequest;
import fr.crepin.microserviceuserbackend.dto.UserResponse;
import fr.crepin.microserviceuserbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "${front.url}")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PutMapping("/switch-role")
    public UserResponse switchRole(
            @RequestBody UserRequest request
    ) {
        try {
            return this.service.switchTenantOwner(request);
        } catch (Exception e) {
            return UserResponse.builder().isValid(false).message("Unable to switch role").build();
        }
    }
}
