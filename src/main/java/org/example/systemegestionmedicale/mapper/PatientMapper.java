package org.example.systemegestionmedicale.mapper;

import org.example.systemegestionmedicale.DTO.PatientDTO;
import org.example.systemegestionmedicale.model.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    PatientDTO toDTO(Patient patient);
    Patient toEntity(PatientDTO patientDTO);

}
