package org.example.systemegestionmedicale.service;

import lombok.RequiredArgsConstructor;
import org.example.systemegestionmedicale.DTO.MedecinDTO;
import org.example.systemegestionmedicale.Repository.MedecinRepository;
import org.example.systemegestionmedicale.mapper.MedecinMapper;
import org.example.systemegestionmedicale.model.Medecin;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedecinService {
    private final MedecinRepository medecinRepository;
    private final MedecinMapper medecinMapper;
    private final org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    @CacheEvict(value = "patients", allEntries = true)
    public MedecinDTO ajouterMedecin(MedecinDTO dto){
        Medecin medecin=medecinMapper.toEntity(dto);
        medecin.setUsername(dto.getUsername());
        medecin.setPassword(passwordEncoder.encode(dto.getPassword()));
        if (medecin.getRole() == null) {
            medecin.setRole(org.example.systemegestionmedicale.model.Role.MEDECIN);
        }
        Medecin saved =medecinRepository.save(medecin);
        return medecinMapper.toDTO(saved);
    }
    @Cacheable(value = "medecins")
    public List<MedecinDTO> getAllMedecins(){
        return medecinRepository.findAll().stream().map(medecinMapper::toDTO)
                .collect(Collectors.toList());
    }
    @Cacheable(value = "patients", key = "#id")
    public MedecinDTO getMedecinById(Long id){
        Medecin medecin = medecinRepository.findById(id)
                .orElseThrow(()->new RuntimeException("medecin introuvable"));
        return medecinMapper.toDTO(medecin);
    }
    @CacheEvict(value = "medecins", allEntries = true)
    public MedecinDTO modifierMedecin(Long id, MedecinDTO dto){
         Medecin medecin= medecinRepository.findById(id)
                .orElseThrow(()->new RuntimeException("medecin introuvable"));
        medecinMapper.updateEntityFromDto(dto,medecin);
        return medecinMapper.toDTO(medecinRepository.save(medecin));
    }
    @CacheEvict(value = "medecins", allEntries = true)
    public void delete(Long id){
        medecinRepository.deleteById(id);
    }

    @Cacheable(value = "medecins")
    public List<MedecinDTO> listerPatients(){
        return medecinRepository.findAll().stream()
                .map(medecin -> medecinMapper.toDTO(medecin)).toList();
    }

    public Page<MedecinDTO> getAllMedecins(Pageable pageable){
        return medecinRepository.findAll(pageable).map(medecinMapper::toDTO);
    }
    public Page<MedecinDTO> searchBySpecialite(String specialite, Pageable pageable){
        return medecinRepository.findBySpecialite(specialite,pageable).map(medecinMapper::toDTO);
    }
}
