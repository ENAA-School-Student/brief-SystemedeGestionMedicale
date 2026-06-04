package org.example.systemegestionmedicale.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Le nom est obligatoire")

    private String username;
    private String password;
}