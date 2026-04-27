package org.example.systemegestionmedicale.Repository;

import org.example.systemegestionmedicale.model.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin,Long> {

}
