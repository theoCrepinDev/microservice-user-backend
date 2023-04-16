package fr.crepin.microserviceuserbackend.dto;

import fr.crepin.microserviceuserbackend.dao.entity.UserData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersResponse {
    private Boolean isValid;
    private String message;
    private List<UserData> user;
}
