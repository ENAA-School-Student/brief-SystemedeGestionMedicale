package org.example.systemegestionmedicale.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.systemegestionmedicale.DTO.PatientDTO;
import org.example.systemegestionmedicale.service.PatientServie;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/patients")
public class PatientController {
    private final PatientServie patientServie;

    @PostMapping
    public PatientDTO createPatient(@Valid @RequestBody PatientDTO dto){
        return patientServie.ajouterPatient(dto);
    }

    @GetMapping
    public List<PatientDTO> getAllPatients(){
        return patientServie.getAllPatients();
    }
    @GetMapping("/{id}")
    public PatientDTO getById(@PathVariable Long id){
        return patientServie.getPatientById(id);
    }
    @PutMapping("/{id}")
    public PatientDTO updatePatient(@PathVariable Long id,@Valid @RequestBody PatientDTO dto){
        return patientServie.modifierPatient(id,dto);
    }
    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id){
         patientServie.delete(id);
    }
}
