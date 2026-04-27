package org.example.systemegestionmedicale.mapper;

import org.example.systemegestionmedicale.DTO.PatientDTO;
import org.example.systemegestionmedicale.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    PatientDTO toDTO(Patient patient);
    Patient toEntity(PatientDTO patientDTO);
    @Mapping(target = "id",ignore=true)
    Patient updateEntityFromDto(PatientDTO dto, @MappingTarget Patient entity);

}
