package org.example.systemegestionmedicale.service;

import jakarta.transaction.Transactional;
import org.example.systemegestionmedicale.DTO.DossierMedicalDTO;
import org.example.systemegestionmedicale.DTO.PatientDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
@ActiveProfiles("test")
@SpringBootTest
@Transactional
class DossierMedicalServiceTest {
    @Autowired
    DossierMedicalService dossierMedicalService;
    @Autowired
    PatientService patientService;

    @Test
    void creerDossier() {
        PatientDTO patient = new PatientDTO();
        patient.setNom("Sara");
        PatientDTO savedPatient = patientService.ajouterPatient(patient);


        DossierMedicalDTO dossierDTO = new DossierMedicalDTO();
        dossierDTO.setPatientId(savedPatient.getId());
        DossierMedicalDTO savedDossier = dossierMedicalService.creerDossier(dossierDTO);


        DossierMedicalDTO updated = dossierMedicalService
                .ajouterDiagnostic(savedDossier.getId(), "Grippe");


        assertNotNull(updated);
        assertEquals("Grippe", updated.getDiagnostic());
    }

    @Test
    void testAjouterDiagnostic_NotFound() {
        assertThrows(RuntimeException.class, () -> {
            dossierMedicalService.ajouterDiagnostic(999L, "Test");
        });
    }
    @Test
    void testAjouterDiagnostic() {

        PatientDTO patient = new PatientDTO();
        patient.setNom("Sara");
        PatientDTO savedPatient = patientService.ajouterPatient(patient);


        DossierMedicalDTO dossierDTO = new DossierMedicalDTO();
        dossierDTO.setPatientId(savedPatient.getId());
        DossierMedicalDTO savedDossier = dossierMedicalService.creerDossier(dossierDTO);


        DossierMedicalDTO updated = dossierMedicalService
                .ajouterDiagnostic(savedDossier.getId(), "Grippe");


        assertNotNull(updated);
        assertEquals("Grippe", updated.getDiagnostic());
    }

    }


