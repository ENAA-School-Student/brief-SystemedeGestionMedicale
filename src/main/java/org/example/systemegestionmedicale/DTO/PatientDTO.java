package org.example.systemegestionmedicale.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientDTO {
    private Long id;
    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Email invalide")
    private String email;

    @Size(min =10,max = 10,message = "Numéro de téléphone invalide")
    private String telephone;
    @Past(message = "La date de naissance doit être dans le passé")
    private LocalDate dateNaissance;
}
