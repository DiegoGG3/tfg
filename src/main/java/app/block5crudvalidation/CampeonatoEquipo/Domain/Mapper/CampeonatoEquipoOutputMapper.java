package app.block5crudvalidation.CampeonatoEquipo.Domain.Mapper;


import app.block5crudvalidation.CampeonatoEquipo.Domain.Entities.CampeonatoEquipo;
import app.block5crudvalidation.CampeonatoEquipo.Infraestructure.DTO.CampeonatoEquipoOutputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CampeonatoEquipoOutputMapper {

    CampeonatoEquipoOutputDTO OutputCampeonatoEquipoToCampeonatoEquipoDto(CampeonatoEquipo campeonatoEquipo);
}
