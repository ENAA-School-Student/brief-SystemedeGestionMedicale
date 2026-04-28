package org.example.systemegestionmedicale.service;

import lombok.RequiredArgsConstructor;
import org.example.systemegestionmedicale.DTO.DossierMedicalDTO;
import org.example.systemegestionmedicale.Repository.DossierMedicalRepository;
import org.example.systemegestionmedicale.Repository.MedecinRepository;
import org.example.systemegestionmedicale.Repository.PatientRepository;
import org.example.systemegestionmedicale.mapper.DossierMedicalMapper;
import org.example.systemegestionmedicale.model.DossierMedical;
import org.example.systemegestionmedicale.model.Patient;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DossierMedicalservice {
    private final DossierMedicalRepository dossierMedicalRepository;
    private final DossierMedicalMapper dossierMedicalMapper;
    private final PatientRepository patientRepository;

    public DossierMedicalDTO creerDossier(DossierMedicalDTO dto){
        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(()->new RuntimeException("patient introuvable"));
        if (dossierMedicalRepository.findByPatientId(dto.getPatientId()).isPresent()){
            throw new RuntimeException("ce patient a deja un dossier");
        }
        DossierMedical dossier = dossierMedicalMapper.toEntity(dto);
        dossier.setPatient(patient);
        if(dossier.getDateCreation() == null){
            dossier.setDateCreation(LocalDate.now());
        }
        return dossierMedicalMapper.toDTO(dossierMedicalRepository.save(dossier));
    }
}
