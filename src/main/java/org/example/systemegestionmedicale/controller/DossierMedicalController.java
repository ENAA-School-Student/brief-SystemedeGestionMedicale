package org.example.systemegestionmedicale.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.systemegestionmedicale.DTO.DossierMedicalDTO;
import org.example.systemegestionmedicale.service.DossierMedicalService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dossiersMedical")
@RequiredArgsConstructor
public class DossierMedicalController {
    private final DossierMedicalService dossierMedicalService;

    @PostMapping
    public DossierMedicalDTO creerDossier(@Valid @RequestBody DossierMedicalDTO dto){
        return dossierMedicalService.creerDossier(dto);
    }
    @PutMapping("/{id}/diagnostic")
    public DossierMedicalDTO ajouterDiagnostic(
            @PathVariable Long id,
            @RequestParam String diagnostic
    ) {
        return dossierMedicalService.ajouterDiagnostic(id, diagnostic);
    }
    @PutMapping("/{id}/observation")
    public DossierMedicalDTO ajouterObservation(
            @PathVariable Long id,
            @RequestParam String observation
    ) {
        return dossierMedicalService.ajouterObservation(id, observation);
    }
    @GetMapping("/patient/{patientId}")
    public DossierMedicalDTO consulterParPatient(@PathVariable Long patientId){
        return dossierMedicalService.consulterParPatient(patientId);
    }


}
