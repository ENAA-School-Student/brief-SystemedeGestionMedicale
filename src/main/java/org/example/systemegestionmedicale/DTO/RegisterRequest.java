package org.example.systemegestionmedicale.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message = "Le nom est obligatoire")
    private String username;
    @NotBlank(message = "L'email est obligatoire")
    private String email;
    private String password;
}