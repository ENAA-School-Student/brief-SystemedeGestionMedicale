package org.example.systemegestionmedicale.mapper;

import org.example.systemegestionmedicale.DTO.MedecinDTO;
import org.example.systemegestionmedicale.DTO.PatientDTO;
import org.example.systemegestionmedicale.model.Medecin;
import org.example.systemegestionmedicale.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MedecinMapper {
    MedecinDTO toDTO(Medecin medecin);
    Medecin toEntity(MedecinDTO medecinDTO);
    @Mapping(target = "id",ignore=true)
    Medecin updateEntityFromDto(MedecinDTO dto, @MappingTarget Medecin entity);

}
