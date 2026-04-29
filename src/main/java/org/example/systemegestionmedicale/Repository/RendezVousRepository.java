package org.example.systemegestionmedicale.Repository;

import org.example.systemegestionmedicale.model.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous,Long> {
    List<RendezVous> findByPatientId(Long patientId);
    @Query("select r from RendezVous r where r.medecin=:id")
    List<RendezVous> findByMedecinId(@Param("id") Long medecinId);
}
