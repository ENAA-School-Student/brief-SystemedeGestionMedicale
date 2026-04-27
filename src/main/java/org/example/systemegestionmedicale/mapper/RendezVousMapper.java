package org.example.systemegestionmedicale.mapper;

import org.example.systemegestionmedicale.DTO.RendezVousDTO;
import org.example.systemegestionmedicale.model.RendezVous;
import org.mapstruct.Mapping;

public interface RendezVousMapper {
  @Mapping(source="patient.id" ,target = "patientId")
  @Mapping(source="medecin.id" ,target = "medecinId")
    RendezVousDTO toDTO(RendezVous rendezVous);
    @Mapping(source="patientID" ,target = "patient.id")
    @Mapping(source="medecinId" ,target = "medecin.id")
    RendezVous toEntity(RendezVousDTO rendezVousDTO);





}
