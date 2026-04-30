package org.example.systemegestionmedicale.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.systemegestionmedicale.DTO.RendezVousDTO;
import org.example.systemegestionmedicale.service.RendezVousService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
 @RequestMapping("/api/rendez-vous")
 @RequiredArgsConstructor
@Tag(name = "rendez-vous",description = "gestion de rendez-vous")
public class RendezVousController {
    private final RendezVousService rendezVousService;
    @Operation(summary = "créer rendez_vous")
    @PostMapping
    public RendezVousDTO creerRendezVous(@Valid @RequestBody RendezVousDTO dto){
        return rendezVousService.createRDV(dto);
    }
    @Operation(summary = "modifier rendez_vous")
    @PutMapping("/{id}")
    public RendezVousDTO updateRendezVous(@PathVariable Long id,@Valid @RequestBody RendezVousDTO dto){
        return rendezVousService.modifierRendezVous(id,dto);
    }
    @Operation(summary = "annuler un rendez_vous")
    @PutMapping("/{id}/annule")
    public void annuleRDV(@PathVariable Long id){
        rendezVousService.annuleRDV(id);
    }
    @Operation(summary = "Lister tous les rendz_vous")
    @GetMapping
    public List<RendezVousDTO> listertous(){
        return rendezVousService.listertous();
    }
    @Operation(summary = "Trouver un rendez_vous par patientID")
    @GetMapping("/patient/{patientId}")
    public List<RendezVousDTO> chercherParPatient(@PathVariable Long patientId){
        return rendezVousService.rechercherParPatient(patientId);
    }
    @Operation(summary = "Trouver un rendez_vous par medecinID")
    @GetMapping("/medecin/{medecinId}")
    public List<RendezVousDTO> chercherParMedecin(@PathVariable Long medecinId){
        return rendezVousService.rechercherParMedecinId(medecinId);
    }
}
