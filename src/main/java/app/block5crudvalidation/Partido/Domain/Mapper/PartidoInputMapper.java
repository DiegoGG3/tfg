package app.block5crudvalidation.Partido.Domain.Mapper;


import app.block5crudvalidation.Partido.Domain.Entities.Partido;
import app.block5crudvalidation.Partido.Infraestructure.DTO.PartidoInputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PartidoInputMapper {
//    @Mapping(source = "id", target = "id")
//    @Mapping(source = "jornada.id", target = "jornadaId")
//    @Mapping(source = "equipoLocal.id", target = "equipoLocalId")
//    @Mapping(source = "equipoVisitante.id", target = "equipoVisitanteId")
//    @Mapping(source = "golesLocal", target = "golesLocal")
//    @Mapping(source = "golesVisitante", target = "golesVisitante")
//    @Mapping(source = "fechaHora", target = "fechaHora")
    PartidoInputDTO InputPartidoToPartidoDto(Partido partido);
    Partido InputPartidoDtoToPartido(PartidoInputDTO partidoDto);
}
