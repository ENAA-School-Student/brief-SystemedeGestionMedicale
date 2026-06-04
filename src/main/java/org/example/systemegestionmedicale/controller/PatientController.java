package org.example.systemegestionmedicale.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.systemegestionmedicale.DTO.PatientDTO;
import org.example.systemegestionmedicale.service.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/patients")
@Tag(name = "patient",description = "gestion des patients")
public class PatientController {
    private final PatientService patientService;

    @Operation(summary = "ajouter un patient")
    @PostMapping
    public PatientDTO createPatient(@Valid @RequestBody PatientDTO dto){

        return patientService.ajouterPatient(dto);
    }
    @GetMapping("/{id}")
    public PatientDTO getById(@PathVariable Long id){
        return patientService.getPatientById(id);
    }
    @Operation(summary = "modifier un patient")
    @PutMapping("/{id}")
    public PatientDTO updatePatient(@PathVariable Long id,@Valid @RequestBody PatientDTO dto){
        return patientService.modifierPatient(id,dto);
    }
    @Operation(summary = "supprimer un patients")
    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id){
         patientService.delete(id);
    }

    @Operation(summary = "Lister tous les patients avec pagination et tri (ex: ?sort=nom,asc)")
    @GetMapping
    public Page<PatientDTO> getAllPatients(Pageable pageable) {
        return patientService.getAllPatient(pageable);
    }
    @Operation(summary = "Rechercher patient par nom avec pagination")
    @GetMapping("/search")
   public Page<PatientDTO> searchByNom(@RequestParam String nom, Pageable pageable) {
            return patientService.chercherByNom(nom, pageable);
         }

}
