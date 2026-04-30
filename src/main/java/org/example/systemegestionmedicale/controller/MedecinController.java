package org.example.systemegestionmedicale.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.systemegestionmedicale.DTO.MedecinDTO;
import org.example.systemegestionmedicale.service.MedecinService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medecins")
@RequiredArgsConstructor
@Tag(name = "medecins",description = "gestion des medecins")
public class MedecinController {
    private final MedecinService medecinService;
    @Operation(summary = "ajouter un medecin")
    @PostMapping
    public MedecinDTO creatMedecin(@Valid @RequestBody MedecinDTO dto){
        return medecinService.ajouterMedecin(dto);
    }
    @Operation(summary = "Lister tous les medecins")
    @GetMapping
    public List<MedecinDTO> getAllMedecins(){
        return medecinService.getAllMedecins();
    }
    @Operation(summary = "Trouver un medecin par ID")
    @GetMapping("/{id}")
    public MedecinDTO getById(@PathVariable Long id){
        return medecinService.getMedecinById(id);
    }
    @Operation(summary = "modifier un medecin")
    @PutMapping("/{id}")
    public MedecinDTO updatePatient(@PathVariable Long id,@Valid @RequestBody MedecinDTO dto) {
        return medecinService.modifierMedecin(id, dto);
    }
    @Operation(summary = "supprimer un medecin")
    @DeleteMapping("/{id}")
    public void deleteMedecin(@PathVariable Long id){
        medecinService.delete(id);
    }

}
