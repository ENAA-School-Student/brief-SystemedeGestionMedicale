package org.example.systemegestionmedicale.service;

import lombok.RequiredArgsConstructor;
import org.example.systemegestionmedicale.DTO.RendezVousDTO;
import org.example.systemegestionmedicale.Repository.MedecinRepository;
import org.example.systemegestionmedicale.Repository.PatientRepository;
import org.example.systemegestionmedicale.Repository.RendezVousRepository;
import org.example.systemegestionmedicale.mapper.RendezVousMapper;
import org.example.systemegestionmedicale.model.Medecin;
import org.example.systemegestionmedicale.model.Patient;
import org.example.systemegestionmedicale.model.RendezVous;
import org.example.systemegestionmedicale.model.StatutRendezVous;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RendezVousService {
    private final RendezVousRepository rendezVousRepository;
    private final RendezVousMapper rendezVousMapper;
    private final PatientRepository patientRepository;
    private final MedecinRepository medecinRepository;

    public RendezVousDTO createRDV(RendezVousDTO dto){
        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(()-> new RuntimeException("patient non trouvé"));
        Medecin medecin = medecinRepository.findById(dto.getMedecinId())
                .orElseThrow(()-> new RuntimeException("medecin non trouvé"));
        RendezVous rendezVous = rendezVousMapper.toEntity(dto);
        rendezVous.setPatient(patient);
        rendezVous.setMedecin(medecin);
        rendezVous.setStatut(StatutRendezVous.EN_ATTENTE);
        RendezVous saved=rendezVousRepository.save(rendezVous);
        return rendezVousMapper.toDTO(saved);

    }

    public RendezVousDTO modifierRendezVous(Long id,RendezVousDTO dto){
        RendezVous existe = rendezVousRepository.findById(id)
                .orElseThrow(()->new RuntimeException("rendezvous introuvable"));
        existe.setDateRendezVous(dto.getDateRendezVous());
        if (dto.getStatut() != null){
            existe.setStatut(dto.getStatut());
        }
        return rendezVousMapper.toDTO(rendezVousRepository.save(existe));
    }

    public void annuleRDV(Long id){
        RendezVous rendezVous = rendezVousRepository.findById(id)
                .orElseThrow(()->new RuntimeException("rendezvous introuvable"));
        rendezVous.setStatut(StatutRendezVous.ANNULE);
        rendezVousRepository.save(rendezVous);
    }

    public List<RendezVousDTO> listertous(){
        return rendezVousRepository.findAll().stream()
                .map(rendezVous -> rendezVousMapper.toDTO(rendezVous))
                .collect(Collectors.toList());
    }

    public List<RendezVousDTO> rechercherParPatient(Long patientId){
        return rendezVousRepository.findByPatientId(patientId).stream()
                .map(rendezVousMapper::toDTO).collect(Collectors.toList());
    }

    public List<RendezVousDTO> rechercherParMedecinId(Long medecinId){
        return rendezVousRepository.findByMedecinId(medecinId).stream()
                .map(rendezVousMapper::toDTO).collect(Collectors.toList());
    }

    public Void deleteAllRDVPourMedecin(Long medecinId){
        return rendezVousRepository.deleteByMedecinId(medecinId);
    }
}
