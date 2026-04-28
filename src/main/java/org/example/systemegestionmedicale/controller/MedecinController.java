package org.example.systemegestionmedicale.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.systemegestionmedicale.DTO.MedecinDTO;
import org.example.systemegestionmedicale.service.MedecinService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medecins")
@RequiredArgsConstructor
public class MedecinController {
    private final MedecinService medecinService;
    @PostMapping
    public MedecinDTO creatMedecin(@Valid @RequestBody MedecinDTO dto){
        return medecinService.ajouterMedecin(dto);
    }
    @GetMapping
    public List<MedecinDTO> getAllMedecins(){
        return medecinService.getAllMedecins();
    }
    @GetMapping("/{id}")
    public MedecinDTO getById(@PathVariable Long id){
        return medecinService.getMedecinById(id);
    }
    @PutMapping("/{id}")
    public MedecinDTO updatePatient(@PathVariable Long id,@Valid @RequestBody MedecinDTO dto) {
        return medecinService.modifierMedecin(id, dto);
    }
    @DeleteMapping("/{id}")
    public void deleteMedecin(@PathVariable Long id){
        medecinService.delete(id);
    }

}
