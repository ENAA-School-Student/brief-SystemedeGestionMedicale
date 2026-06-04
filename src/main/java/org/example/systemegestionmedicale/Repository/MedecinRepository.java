package org.example.systemegestionmedicale.Repository;

import org.example.systemegestionmedicale.model.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin,Long> {
     @Query("select m from Medecin m where m.email=:email")
     Optional<Medecin> findMedecinByEmail(@Param("email") String email);
}
