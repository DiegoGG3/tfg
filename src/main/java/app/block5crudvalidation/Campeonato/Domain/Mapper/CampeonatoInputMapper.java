package app.block5crudvalidation.Campeonato.Domain.Mapper;


import app.block5crudvalidation.Campeonato.Domain.Entities.Campeonato;
import app.block5crudvalidation.Campeonato.Infraestructure.DTO.CampeonatoInputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CampeonatoInputMapper {
//    @Mapping(source = "id", target = "id")
//    @Mapping(source = "nombre", target = "nombre")
//    @Mapping(source = "formato", target = "formato")
//    @Mapping(source = "fechaInicio", target = "fechaInicio")
//    @Mapping(source = "ganador", target = "ganador")
//    @Mapping(source = "foto", target = "foto")
//    @Mapping(source = "pais", target = "pais")
    CampeonatoInputDTO InputCampeonatoToCampeonatoDto(Campeonato campeonato);
    Campeonato InputCampeonatoDtoToCampeonato(CampeonatoInputDTO campeonatoDto);
}
