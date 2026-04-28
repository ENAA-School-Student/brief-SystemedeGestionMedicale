package org.example.systemegestionmedicale.service;

import lombok.RequiredArgsConstructor;
import org.example.systemegestionmedicale.DTO.PatientDTO;
import org.example.systemegestionmedicale.Repository.PatientRepository;
import org.example.systemegestionmedicale.mapper.PatientMapper;
import org.example.systemegestionmedicale.model.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Patientservie {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;


    public PatientDTO ajouterPatient(PatientDTO dto){
        Patient patient =patientMapper.toEntity(dto);
        Patient  saved =patientRepository.save(patient);
        return patientMapper.toDTO(saved);
    }

    public PatientDTO modifierPatient(Long id,PatientDTO dto){
        Patient patient = patientRepository.findById(id)
                .orElseThrow(()->new RuntimeException("pateint introuvable"));
        patientMapper.updateEntityFromDto(dto,patient);
        return patientMapper.toDTO(patientRepository.save(patient));
    }

    public void delete(Long id){
        patientRepository.deleteById(id);
    }

    public List<PatientDTO> listerPatients(){
        return patientRepository.findAll().stream()
                .map(patient -> patientMapper.toDTO(patient)).toList();
    }

    public PatientDTO consulterPatient(Long id){
        Patient patient = patientRepository.findById(id)
                .orElseThrow(()->new RuntimeException("patient itrouvable avec id :" +id));
        return patientMapper.toDTO(patient);
    }

}
