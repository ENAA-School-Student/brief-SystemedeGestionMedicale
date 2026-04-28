package org.example.systemegestionmedicale.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.systemegestionmedicale.DTO.RendezVousDTO;
import org.example.systemegestionmedicale.service.RendezVousservice;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
 @RequestMapping("/api/rendez-vous")
 @RequiredArgsConstructor
public class RendezVousController {
    private final RendezVousservice rendezVousservice;
    @PostMapping
    public RendezVousDTO creerRendezVous(@Valid @RequestBody RendezVousDTO dto){
        return rendezVousservice.createRDV(dto);
    }
    @PutMapping("/{id}")
    public RendezVousDTO updateRendezVous(@PathVariable Long id,@Valid @RequestBody RendezVousDTO dto){
        return rendezVousservice.modifierRendezVous(id,dto);
    }
    @PutMapping("/{id}/annule")
    public void annuleRDV(@PathVariable Long id){
        rendezVousservice.annuleRDV(id);
    }
    @GetMapping
    public List<RendezVousDTO> listertous(){
        return rendezVousservice.listertous();
    }
    @GetMapping("/patient/{patientId}")
    public List<RendezVousDTO> chercherParPatient(@PathVariable Long patientId){
        return rendezVousservice.rechercherParPatient(patientId);
    }
    @GetMapping("/medecin/{medecinId}")
    public List<RendezVousDTO> chercherParMedecin(@PathVariable Long medecinId){
        return rendezVousservice.rechercherParMedecinId(medecinId);
    }
}
