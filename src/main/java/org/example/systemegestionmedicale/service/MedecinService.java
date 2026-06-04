package org.example.systemegestionmedicale.service;

import lombok.RequiredArgsConstructor;
import org.example.systemegestionmedicale.DTO.MedecinDTO;
import org.example.systemegestionmedicale.Repository.MedecinRepository;
import org.example.systemegestionmedicale.mapper.MedecinMapper;
import org.example.systemegestionmedicale.model.Medecin;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedecinService {
    private final MedecinRepository medecinRepository;
    private final MedecinMapper medecinMapper;

    public MedecinDTO ajouterMedecin(MedecinDTO dto){
        Medecin medecin=medecinMapper.toEntity(dto);
        Medecin saved =medecinRepository.save(medecin);
        return medecinMapper.toDTO(saved);
    }
    public List<MedecinDTO> getAllMedecins(){
        return medecinRepository.findAll().stream().map(medecinMapper::toDTO)
                .collect(Collectors.toList());
    }
    public MedecinDTO getMedecinById(Long id){
        Medecin medecin = medecinRepository.findById(id)
                .orElseThrow(()->new RuntimeException("medecin introuvable"));
        return medecinMapper.toDTO(medecin);
    }
    public MedecinDTO modifierMedecin(Long id, MedecinDTO dto){
         Medecin medecin= medecinRepository.findById(id)
                .orElseThrow(()->new RuntimeException("medecin introuvable"));
        medecinMapper.updateEntityFromDto(dto,medecin);
        return medecinMapper.toDTO(medecinRepository.save(medecin));
    }

    public void delete(Long id){
        medecinRepository.deleteById(id);
    }

    public List<MedecinDTO> listerPatients(){
        return medecinRepository.findAll().stream()
                .map(medecin -> medecinMapper.toDTO(medecin)).toList();
    }
}
