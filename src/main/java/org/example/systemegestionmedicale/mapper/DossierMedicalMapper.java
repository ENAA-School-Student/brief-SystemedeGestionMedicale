package org.example.systemegestionmedicale.mapper;

import org.example.systemegestionmedicale.DTO.DossierMedicalDTO;
import org.example.systemegestionmedicale.model.DossierMedical;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DossierMedicalMapper {
    @Mapping(source = "patient.id",target = "patientId")
    DossierMedicalDTO toDTO(DossierMedical dossierMedical);
    @Mapping(source = "patientId",target = "patient.id")
    DossierMedical toEntity(DossierMedicalDTO dossierMedicalDTO);

}
