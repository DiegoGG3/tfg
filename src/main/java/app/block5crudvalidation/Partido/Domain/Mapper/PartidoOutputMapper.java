package app.block5crudvalidation.Partido.Domain.Mapper;


import app.block5crudvalidation.Partido.Domain.Entities.Partido;
import app.block5crudvalidation.Partido.Infraestructure.DTO.PartidoOutputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PartidoOutputMapper {
//    @Mapping(source = "id", target = "id")
//    @Mapping(source = "jornada.id", target = "jornadaId")
//    @Mapping(source = "equipoLocal.id", target = "equipoLocalId")
//    @Mapping(source = "equipoVisitante.id", target = "equipoVisitanteId")
//    @Mapping(source = "golesLocal", target = "golesLocal")
//    @Mapping(source = "golesVisitante", target = "golesVisitante")
//    @Mapping(source = "fechaHora", target = "fechaHora")
    PartidoOutputDTO OutputPartidoToPartidoDto(Partido partido);
    Partido OutputPartidoDtoToPartido(PartidoOutputDTO partidoDto);
}
