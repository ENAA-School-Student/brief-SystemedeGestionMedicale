package org.example.systemegestionmedicale.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.systemegestionmedicale.DTO.DossierMedicalDTO;
import org.example.systemegestionmedicale.service.DossierMedicalService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dossiersMedical")
@RequiredArgsConstructor
@Tag(name = "DossierMedical",description = "gestion des dossierMedical")
public class DossierMedicalController {
    private final DossierMedicalService dossierMedicalService;

    @Operation(summary = "créer dossierMedecal")
    @PostMapping
    public DossierMedicalDTO creerDossier(@Valid @RequestBody DossierMedicalDTO dto){
        return dossierMedicalService.creerDossier(dto);
    }
    @Operation(summary = "ajouter un diagnostic a dossier")
    @PutMapping("/{id}/diagnostic")
    public DossierMedicalDTO ajouterDiagnostic(
            @PathVariable Long id,
            @RequestParam String diagnostic
    ) {
        return dossierMedicalService.ajouterDiagnostic(id, diagnostic);
    }
    @Operation(summary = "ajouter une observation a dossier")
    @PutMapping("/{id}/observation")
    public DossierMedicalDTO ajouterObservation(
            @PathVariable Long id,
            @RequestParam String observation
    ) {
        return dossierMedicalService.ajouterObservation(id, observation);
    }
    @Operation(summary = "Trouver un dossierMedical par patientID")
    @GetMapping("/patient/{patientId}")
    public DossierMedicalDTO consulterParPatient(@PathVariable Long patientId){
        return dossierMedicalService.consulterParPatient(patientId);
    }


}
