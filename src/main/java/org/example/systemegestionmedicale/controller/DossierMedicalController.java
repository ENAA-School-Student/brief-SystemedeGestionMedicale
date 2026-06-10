package org.example.systemegestionmedicale.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.systemegestionmedicale.DTO.DossierMedicalDTO;
import org.example.systemegestionmedicale.service.DossierMedicalService;
import org.example.systemegestionmedicale.service.PdfGeneratorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/api/dossiersMedical")
@RequiredArgsConstructor
@Tag(name = "DossierMedical",description = "gestion des dossierMedical")
public class DossierMedicalController {
    private final DossierMedicalService dossierMedicalService;
    private final PdfGeneratorService pdfGeneratorService;

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
    @Operation(summary = "Lister tous les dossiers médicaux avec pagination")
    @GetMapping
   public Page<DossierMedicalDTO> getAllDossiers(Pageable pageable) {
            return dossierMedicalService.getAllDossiers(pageable);
         }
    @Operation(summary = "Exporter un dossier médical en PDF")
    @GetMapping("/{patientId}/export/pdf")
    public ResponseEntity<InputStreamResource> exportToPdf(@PathVariable Long patientId) {


        DossierMedicalDTO dossier = dossierMedicalService.consulterParPatient(patientId);


        ByteArrayInputStream bis = pdfGeneratorService.generateDossierMedicalPdf(dossier);


        HttpHeaders headers = new HttpHeaders();
        headers.add(
                "Content-Disposition",
                "attachment; filename=dossier_medical_" + patientId + ".pdf"
        );

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}

