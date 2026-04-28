package org.example.systemegestionmedicale.Repository;

import org.example.systemegestionmedicale.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    boolean findByEmail(void attr0);
}
