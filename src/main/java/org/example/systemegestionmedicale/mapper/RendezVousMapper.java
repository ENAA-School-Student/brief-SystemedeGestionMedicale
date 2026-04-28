package org.example.systemegestionmedicale.mapper;

import org.example.systemegestionmedicale.DTO.PatientDTO;
import org.example.systemegestionmedicale.DTO.RendezVousDTO;
import org.example.systemegestionmedicale.model.Patient;
import org.example.systemegestionmedicale.model.RendezVous;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RendezVousMapper {
  @Mapping(source="patient.id" ,target = "patientId")
  @Mapping(source="medecin.id" ,target = "medecinId")
    RendezVousDTO toDTO(RendezVous rendezVous);
    @Mapping(source="patientID" ,target = "patient.id")
    @Mapping(source="medecinId" ,target = "medecin.id")
    RendezVous toEntity(RendezVousDTO rendezVousDTO);

  @Mapping(target = "id",ignore=true)
  RendezVous updateEntityFromDto(RendezVousDTO dto, @MappingTarget RendezVous entity);
}
