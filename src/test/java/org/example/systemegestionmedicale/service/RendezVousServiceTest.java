package org.example.systemegestionmedicale.service;

import jakarta.transaction.Transactional;
import org.example.systemegestionmedicale.DTO.RendezVousDTO;
import org.example.systemegestionmedicale.Repository.MedecinRepository;
import org.example.systemegestionmedicale.Repository.PatientRepository;
import org.example.systemegestionmedicale.Repository.RendezVousRepository;
import org.example.systemegestionmedicale.model.Medecin;
import org.example.systemegestionmedicale.model.Patient;
import org.example.systemegestionmedicale.model.RendezVous;
import org.example.systemegestionmedicale.model.StatutRendezVous;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
    @Autowired
    private RendezVousRepository rendezVousRepository;

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
        RendezVousDTO created =rendezVousService.createRDV(dto);
        RendezVousDTO updateDto = new RendezVousDTO();
        updateDto.setDateRendezVous(LocalDateTime.now().plusDays(2));
        updateDto.setStatut(StatutRendezVous.CONFIRME);

        RendezVousDTO updated =rendezVousService.modifierRendezVous(created.getId(),updateDto);
        assertNotNull(updated);
        assertEquals(StatutRendezVous.CONFIRME,updated.getStatut());


    }

    @Test
    void annuleRDV() {

        Medecin medecin = medecinRepository.save(
                Medecin.builder()
                        .nom("Dr Test")
                        .email("test2@example.com")
                        .telephone("0611111111")
                        .specialite("Cardio")
                        .build()
        );

        Patient patient = patientRepository.save(
                Patient.builder()
                        .nom("Test")
                        .prenom("User")
                        .email("user2@example.com")
                        .telephone("0622222222")
                        .dateNaissance(LocalDate.of(1990, 2, 2))
                        .build()
        );

        RendezVousDTO dto = new RendezVousDTO();
        dto.setPatientId(patient.getId());
        dto.setMedecinId(medecin.getId());
        dto.setDateRendezVous(LocalDateTime.now().plusDays(1));

        RendezVousDTO created = rendezVousService.createRDV(dto);

        rendezVousService.annuleRDV(created.getId());

        RendezVous updated = rendezVousRepository.findById(created.getId()).orElseThrow();

        assertEquals(StatutRendezVous.ANNULE, updated.getStatut());
    }

    @Test
    void rechercherParPatient() {
            Medecin medecin = medecinRepository.save(
                    Medecin.builder()
                            .nom("Dr Test")
                            .email("test3@example.com")
                            .telephone("0611111111")
                            .specialite("Dermato")
                            .build()
            );

            Patient patient = patientRepository.save(
                    Patient.builder()
                            .nom("Test")
                            .prenom("User")
                            .email("user3@example.com")
                            .telephone("0622222222")
                            .dateNaissance(LocalDate.of(1988, 3, 3))
                            .build()
            );

            RendezVousDTO dto = new RendezVousDTO();
            dto.setPatientId(patient.getId());
            dto.setMedecinId(medecin.getId());
            dto.setDateRendezVous(LocalDateTime.now().plusDays(1));

            rendezVousService.createRDV(dto);

            List<RendezVousDTO> list = rendezVousService.rechercherParPatient(patient.getId());

            assertFalse(list.isEmpty());
            assertEquals(patient.getId(), list.get(0).getPatientId());

    }
}