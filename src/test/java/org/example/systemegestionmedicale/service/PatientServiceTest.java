package org.example.systemegestionmedicale.service;

import jakarta.transaction.Transactional;
import org.example.systemegestionmedicale.DTO.PatientDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
@ActiveProfiles("test")
@SpringBootTest
@Transactional
class PatientServiceTest {
    @Autowired
    PatientService patientService;

    @Test
    void ajouterPatient() {
        PatientDTO dto = new PatientDTO();
        dto.setNom("Ali");
        dto.setPrenom("Ahmed");
        dto.setEmail("ali.ahmed@example.com");


        PatientDTO result = patientService.ajouterPatient(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals("Ali", result.getNom());
    }
}