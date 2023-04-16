package fr.crepin.microserviceuserbackend.services;

import fr.crepin.microserviceuserbackend.dao.entity.UserData;
import fr.crepin.microserviceuserbackend.dao.repository.UserDataRepository;
import fr.crepin.microserviceuserbackend.dto.UserRequest;
import fr.crepin.microserviceuserbackend.dto.UserResponse;
import fr.crepin.microserviceuserbackend.exception.DataValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDataRepository repository;

    @Autowired
    public UserServiceImpl(UserDataRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserResponse switchTenantOwner(UserRequest request) throws DataValidationException {
        if (!List.of("OWNER", "TENANT", "ADMIN").contains(request.getNewrole().toUpperCase())) {
            throw new DataValidationException("Not a valid role");
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
        var oldRole = user.getRole();
        oldRole.setRole(request.getNewrole());
        user.setRole(oldRole);
        try {
            repository.save(user);
        } catch (Exception e) {
            throw e;
        }
        return UserResponse.builder().isValid(true).user(user).message("Le rôle à bien été changé").build();
    }

    @Override
    public List<UserData> findAllUsers() {
        return repository.findAll();
    }
}
