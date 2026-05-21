package org.example.systemegestionmedicale.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.systemegestionmedicale.DTO.MedecinDTO;
import org.example.systemegestionmedicale.service.MedecinService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Operation(summary = "Lister tous les médecins avec pagination et tri (ex: ?sort=specialite,desc)")
     @GetMapping
    public Page<MedecinDTO> getAllMedecins(Pageable pageable) {
             return medecinService.getAllMedecins(pageable);
        }

        @Operation(summary = "Rechercher médecin par spécialité avec pagination")
   @GetMapping("/search")
   public Page<MedecinDTO> searchBySpecialite(@RequestParam String specialite, Pageable pageable) {
           return medecinService.searchBySpecialite(specialite, pageable);
        }

}
