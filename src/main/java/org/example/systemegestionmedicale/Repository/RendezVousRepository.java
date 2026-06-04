package org.example.systemegestionmedicale.Repository;

import org.example.systemegestionmedicale.model.RendezVous;
import org.example.systemegestionmedicale.model.StatutRendezVous;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous,Long> {
    List<RendezVous> findByPatientId(Long patientId);
    List<RendezVous> findByMedecinId(Long medecinId);

    Void deleteByMedecinId(@Param("medecinId") Long medecinId);

    Page<RendezVous> findByStatut(StatutRendezVous statut, Pageable pageable);

    Page<RendezVous> findAll(Pageable pageable);
    Page<RendezVous> findByDate_rendez_vous(Date date_rendez_vous,Pageable pageable);
}
