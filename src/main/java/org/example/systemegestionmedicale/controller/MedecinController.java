package org.example.systemegestionmedicale.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.systemegestionmedicale.DTO.MedecinDTO;
import org.example.systemegestionmedicale.DTO.PatientDTO;
import org.example.systemegestionmedicale.service.Medecinservice;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medecins")
@RequiredArgsConstructor
public class MedecinController {
    private final Medecinservice medecinservice;
    @PostMapping
    public MedecinDTO creatMedecin(@Valid @RequestBody MedecinDTO dto){
        return medecinservice.ajouterMedecin(dto);
    }
    @GetMapping
    public List<MedecinDTO> getAllMedecins(){
        return medecinservice.getAllMedecins();
    }
    @GetMapping
    public MedecinDTO getById(@PathVariable Long id){
        return medecinservice.getMedecinById(id);
    }
    @PutMapping
    public MedecinDTO updatePatient(@PathVariable Long id,@Valid @RequestBody MedecinDTO dto) {
        return medecinservice.modifierMedecin(id, dto);
    }
    @DeleteMapping("/{id}")
    public void deleteMedecin(@PathVariable Long id){
        medecinservice.delete(id);
    }


}
