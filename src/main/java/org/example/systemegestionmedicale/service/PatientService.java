package org.example.systemegestionmedicale.service;

import lombok.RequiredArgsConstructor;
import org.example.systemegestionmedicale.DTO.PatientDTO;
import org.example.systemegestionmedicale.Repository.DossierMedicalRepository;
import org.example.systemegestionmedicale.Repository.PatientRepository;
import org.example.systemegestionmedicale.mapper.PatientMapper;
import org.example.systemegestionmedicale.model.DossierMedical;
import org.example.systemegestionmedicale.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;



    public PatientDTO ajouterPatient(PatientDTO dto){
        Patient patient =patientMapper.toEntity(dto);
        patient.setUsername(dto.getUsername());
        patient.setPassword(passwordEncoder.encode(dto.getPassword()));
        if (patient.getRole() == null) {
            patient.setRole(org.example.systemegestionmedicale.model.Role.PATIENT);
        }
        Patient  saved =patientRepository.save(patient);

        return patientMapper.toDTO(saved);
    }
    public List<PatientDTO> getAllPatients(){
        return patientRepository.findAll().stream().map(patientMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PatientDTO getPatientById(Long id){
        Patient patient = patientRepository.findById(id)
                .orElseThrow(()->new RuntimeException("patient non trouvé"));
        return patientMapper.toDTO(patient);
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
    public Page<PatientDTO> getAllPatient(Pageable pageable){
        return patientRepository.findAll(pageable).map(patientMapper::toDTO);
    }
    public Page<PatientDTO> chercherByNom(String nom,Pageable pageable){
        return patientRepository.findByNom(nom,pageable).map(patient -> patientMapper.toDTO(patient));
    }

}
