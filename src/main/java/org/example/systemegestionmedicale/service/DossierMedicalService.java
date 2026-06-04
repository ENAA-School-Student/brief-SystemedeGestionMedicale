package org.example.systemegestionmedicale.service;

import lombok.RequiredArgsConstructor;
import org.example.systemegestionmedicale.DTO.DossierMedicalDTO;
import org.example.systemegestionmedicale.Repository.DossierMedicalRepository;
import org.example.systemegestionmedicale.Repository.PatientRepository;
import org.example.systemegestionmedicale.mapper.DossierMedicalMapper;
import org.example.systemegestionmedicale.model.DossierMedical;
import org.example.systemegestionmedicale.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DossierMedicalService {
    private final DossierMedicalRepository dossierMedicalRepository;
    private final DossierMedicalMapper dossierMedicalMapper;
    private final PatientRepository patientRepository;

    public DossierMedicalDTO creerDossier(DossierMedicalDTO dto){
        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(()->new RuntimeException("patient introuvable"));
        if (dossierMedicalRepository.existsByPatientId(dto.getPatientId())){
            throw new RuntimeException("ce patient a deja un dossier");
        }
        DossierMedical dossier = dossierMedicalMapper.toEntity(dto);
        dossier.setPatient(patient);
        if(dossier.getDateCreation() == null){
            dossier.setDateCreation(LocalDate.now());
        }
        return dossierMedicalMapper.toDTO(dossierMedicalRepository.save(dossier));
    }

    public DossierMedicalDTO ajouterDiagnostic(Long id, String diagnostic){
        DossierMedical dossier = dossierMedicalRepository.findById(id)
                .orElseThrow(()->new RuntimeException("dossier medical introuvable"));
        dossier.setDiagnostic(diagnostic);
        return dossierMedicalMapper.toDTO(dossierMedicalRepository.save(dossier));

    }
    public DossierMedicalDTO ajouterObservation(Long id, String observation){
        DossierMedical dossier = dossierMedicalRepository.findById(id)
                .orElseThrow(()->new RuntimeException("dossier medical introuvable"));
        dossier.setObservations(observation);
        return dossierMedicalMapper.toDTO(dossierMedicalRepository.save(dossier));
    }

    public DossierMedicalDTO consulterParPatient(Long patientId){
        DossierMedical dossier = dossierMedicalRepository.findByPatientId(patientId)
                .orElseThrow(() -> new RuntimeException("Dossier non trouvé pour ce patient"));
        return dossierMedicalMapper.toDTO(dossier);
    }
    public Page<DossierMedicalDTO> getAllDossiers(Pageable pageable) {
           return dossierMedicalRepository.findAll(pageable).map(dossierMedicalMapper::toDTO);
    }
}
