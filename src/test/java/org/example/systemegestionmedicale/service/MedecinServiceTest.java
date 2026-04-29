package org.example.systemegestionmedicale.service;

import jakarta.transaction.Transactional;
import org.example.systemegestionmedicale.DTO.MedecinDTO;
import org.example.systemegestionmedicale.DTO.PatientDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
@ActiveProfiles("test")
@SpringBootTest
@Transactional
class MedecinServiceTest {
    @Autowired
    MedecinService medecinService;

    @Test
    void modifierMedecin() {
        MedecinDTO dto = new MedecinDTO();
        dto.setNom("Ali");

        MedecinDTO saved = medecinService.modifierMedecin(1L,dto);

        MedecinDTO updateDTO = new MedecinDTO();
        updateDTO.setNom("Karim");


        MedecinDTO updated = medecinService.modifierMedecin(saved.getId(), updateDTO);


        assertNotNull(updated);
        assertEquals(saved.getId(), updated.getId());
        assertEquals("Karim", updated.getNom());
    }
}