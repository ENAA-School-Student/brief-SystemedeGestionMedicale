package org.example.systemegestionmedicale.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DossierMedicalDTO {
    private Long id;
    @NotBlank(message = "diagnostic est obligatoire")
    private String diagnostic;
    @NotBlank(message = "observation est obligatoire")
    private String observations;

    @NotNull(message = "La date de création est obligatoire")
    private LocalDate dateCreation;

    @NotNull(message = "L'ID du patient est obligatoire")
    private Long patientId;
}
