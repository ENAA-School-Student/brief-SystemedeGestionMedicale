package org.example.systemegestionmedicale.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.systemegestionmedicale.DTO.PatientDTO;
import org.example.systemegestionmedicale.service.Patientservie;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/patients")
public class PatientController {
    private final Patientservie patientservie;

    @PostMapping
    public PatientDTO createPatient(@Valid @RequestBody PatientDTO dto){
        return patientservie.ajouterPatient(dto);
    }

    @GetMapping
    public List<PatientDTO> getAllPatients(){
        return patientservie.getAllPatients();
    }
    @GetMapping
    public PatientDTO getById(@PathVariable Long id){
        return patientservie.getPatientById(id);
    }
    @PutMapping
    public PatientDTO updatePatient(@PathVariable Long id,@Valid @RequestBody PatientDTO dto){
        return patientservie.modifierPatient(id,dto);
    }
    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id){
         patientservie.delete(id);
    }
}
