package app.block5crudvalidation.Incidencias.Domain.Mapper;


import app.block5crudvalidation.Incidencias.Domain.Entities.Incidencias;
import app.block5crudvalidation.Incidencias.Infraestructure.DTO.IncidenciasInputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IncidenciasInputMapper {
//    @Mapping(source = "id", target = "id")
//    @Mapping(source = "partido.id", target = "partidoId")
//    @Mapping(source = "jugador.id", target = "jugadorId")
//    @Mapping(source = "tipo", target = "tipo")
//    @Mapping(source = "minuto", target = "minuto")
//    @Mapping(source = "descripcion", target = "descripcion")
    IncidenciasInputDTO InputIncidenciasToIncidenciasDto(Incidencias incidencias);
    Incidencias InputIncidenciasDtoToIncidencias(IncidenciasInputDTO incidenciasDto);
}
