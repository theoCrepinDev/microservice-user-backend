package fr.crepin.microserviceuserbackend.config;

import fr.crepin.microserviceuserbackend.dao.entity.UserData;
import fr.crepin.microserviceuserbackend.dao.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfig {
    private final UserDataRepository userDataRepository;

    @Autowired
    public ApplicationConfig(UserDataRepository repository) {
        userDataRepository = repository;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            try {

                UserData userData = userDataRepository.findByEmailOrUsername(username);
                if (username == null) {
                    throw new UsernameNotFoundException("User not found");
                }
                return userData;
            } catch (Exception e) {

                throw new UsernameNotFoundException("User not found");
            }
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider(
            @Autowired UserDetailsService userDetailsService,
            @Autowired PasswordEncoder passwordEncoder
    ) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            @Autowired AuthenticationConfiguration config
    ) throws Exception {
        return config.getAuthenticationManager();
    }

}
