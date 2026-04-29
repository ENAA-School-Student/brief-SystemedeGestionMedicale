package org.example.systemegestionmedicale.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.systemegestionmedicale.DTO.PatientDTO;
import org.example.systemegestionmedicale.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/patients")
public class PatientController {
    private final PatientService patientService;

    @PostMapping
    public PatientDTO createPatient(@Valid @RequestBody PatientDTO dto){
        return patientService.ajouterPatient(dto);
    }

    @GetMapping
    public List<PatientDTO> getAllPatients(){
        return patientService.getAllPatients();
    }
    @GetMapping("/{id}")
    public PatientDTO getById(@PathVariable Long id){
        return patientService.getPatientById(id);
    }
    @PutMapping("/{id}")
    public PatientDTO updatePatient(@PathVariable Long id,@Valid @RequestBody PatientDTO dto){
        return patientService.modifierPatient(id,dto);
    }
    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id){
         patientService.delete(id);
    }
}
