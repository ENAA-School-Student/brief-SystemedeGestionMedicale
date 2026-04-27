package org.example.systemegestionmedicale.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedecinDTO {
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    @Size(max = 100, message = "Le nom ne doit pas dépasser 100 caractères")
    private String nom;

    @NotBlank(message = "La spécialité est obligatoire")
    @Size(max = 100, message = "La spécialité ne doit pas dépasser 100 caractères")
    private String specialite;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Email invalide")
    @Size(max = 150)
    private String email;

    @Size(min = 10, max = 15, message = "Numéro de téléphone invalide")
    private String telephone;
}
