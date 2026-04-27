package org.example.systemegestionmedicale.mapper;

import org.example.systemegestionmedicale.DTO.MedecinDTO;
import org.example.systemegestionmedicale.model.Medecin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "sring")
public interface MedecinMapper {
    MedecinDTO toDTO(Medecin medecin);
    Medecin toEntity(MedecinDTO medecinDTO);

}
