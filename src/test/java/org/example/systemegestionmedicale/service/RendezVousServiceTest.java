package org.example.systemegestionmedicale.service;

import jakarta.transaction.Transactional;
import org.example.systemegestionmedicale.DTO.RendezVousDTO;
import org.example.systemegestionmedicale.Repository.MedecinRepository;
import org.example.systemegestionmedicale.Repository.PatientRepository;
import org.example.systemegestionmedicale.model.Medecin;
import org.example.systemegestionmedicale.model.Patient;
import org.example.systemegestionmedicale.model.StatutRendezVous;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@ActiveProfiles("test")
@SpringBootTest
@Transactional
class RendezVousServiceTest {
    @Autowired
    private RendezVousService rendezVousService;
    @Autowired
    private MedecinRepository medecinRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Test
    void createRDV() {
        Medecin medecin = new Medecin();
        medecin.setNom("Dr Ahmed");
        medecin.setEmail("ahmed@example.com");
        medecin.setTelephone("0612345678");
        medecin.setSpecialite("Cardiologie");
        medecin = medecinRepository.save(medecin);

        Patient patient = new Patient();
        patient.setNom("El");
        patient.setPrenom("Kamal");
        patient.setEmail("kamal@example.com");
        patient.setTelephone("0612345678");
        patient.setDateNaissance(LocalDate.of(1998, 5, 15));
        patient = patientRepository.save(patient);

        RendezVousDTO dto = new RendezVousDTO();
        dto.setPatientId(patient.getId());
        dto.setMedecinId(medecin.getId());
        dto.setDateRendezVous(LocalDateTime.now().plusDays(1));

        RendezVousDTO result = rendezVousService.createRDV(dto);

        assertNotNull(result);
        assertEquals(StatutRendezVous.EN_ATTENTE, result.getStatut());
    }

    @Test
    void modifierRendezVous() {

    }

    @Test
    void annuleRDV() {
    }

    @Test
    void rechercherParPatient() {
    }
}