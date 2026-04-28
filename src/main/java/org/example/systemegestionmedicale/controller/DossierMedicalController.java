package org.example.systemegestionmedicale.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.systemegestionmedicale.DTO.DossierMedicalDTO;
import org.example.systemegestionmedicale.service.DossierMedicalservice;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dossiersMedical")
@RequiredArgsConstructor
public class DossierMedicalController {
    private final DossierMedicalservice dossierMedicalservice;

    @PostMapping
    public DossierMedicalDTO creerDossier(@Valid @RequestBody DossierMedicalDTO dto){
        return dossierMedicalservice.creerDossier(dto);
    }
    @PutMapping("/{id}/diagnostic")
    public DossierMedicalDTO ajouterDiagnostic(
            @PathVariable Long id,
            @RequestParam String diagnostic
    ) {
        return dossierMedicalservice.ajouterDiagnostic(id, diagnostic);
    }
    @PutMapping("/{id}/observation")
    public DossierMedicalDTO ajouterObservation(
            @PathVariable Long id,
            @RequestParam String observation
    ) {
        return dossierMedicalservice.ajouterObservation(id, observation);
    }
    @GetMapping("/patient/{patientId}")
    public DossierMedicalDTO consulterParPatient(@PathVariable Long patientId){
        return dossierMedicalservice.consulterParPatient(patientId);
    }


}
