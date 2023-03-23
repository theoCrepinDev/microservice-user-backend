package fr.crepin.microserviceuserbackend.dto;

import fr.crepin.microserviceuserbackend.dao.entity.UserData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Boolean isValid;
    private String message;
    private UserData user;
}
