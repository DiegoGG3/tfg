package app.block5crudvalidation.Campeonato.Domain.Mapper;

import app.block5crudvalidation.Campeonato.Domain.Entities.Campeonato;
import app.block5crudvalidation.Campeonato.Infraestructure.DTO.CampeonatoOutputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CampeonatoOutputMapper {
//    @Mapping(source = "id", target = "id")
//    @Mapping(source = "nombre", target = "nombre")
//    @Mapping(source = "formato", target = "formato")
//    @Mapping(source = "fechaInicio", target = "fechaInicio")
//    @Mapping(source = "ganador", target = "ganador")
//    @Mapping(source = "foto", target = "foto")
//    @Mapping(source = "pais", target = "pais")
    CampeonatoOutputDTO OutputCampeonatoToCampeonatoDto(Campeonato campeonato);

    Campeonato OutputCampeonatoDtoToCampeonato(CampeonatoOutputDTO campeonatoDto);
}
