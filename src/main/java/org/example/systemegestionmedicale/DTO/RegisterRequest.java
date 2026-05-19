package org.example.systemegestionmedicale.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.systemegestionmedicale.model.Role;

@Data
public class RegisterRequest {

    @NotBlank(message = "Le nom est obligatoire")
    private String username;
    @NotBlank(message = "L'email est obligatoire")
    private String email;
    @NotBlank(message = "Le mot de passe est obligatoire")
    private String password;
    @NotNull(message = "Le rôle est obligatoire")
    private Role role;
}