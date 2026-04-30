package org.example.systemegestionmedicale.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.systemegestionmedicale.DTO.PatientDTO;
import org.example.systemegestionmedicale.service.PatientService;
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
    @Operation(summary = "Lister tous les patients")
    @GetMapping
    public List<PatientDTO> getAllPatients(){
        return patientService.getAllPatients();
    }
    @Operation(summary = "Trouver un patient par ID")
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
}
