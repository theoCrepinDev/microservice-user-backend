package fr.crepin.microserviceuserbackend.config;

import fr.crepin.microserviceuserbackend.dao.entity.UserData;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface JwtService {
    String extractUsernameOrEmail(String jwt);

    String extaractId(String token);

    String generateToken(UserData userDetails);

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    String generateToken(Map<String, Object> extraClaims,
                         UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);
}
