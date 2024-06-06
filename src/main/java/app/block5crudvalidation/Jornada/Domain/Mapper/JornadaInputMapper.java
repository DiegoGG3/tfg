package app.block5crudvalidation.Jornada.Domain.Mapper;


import app.block5crudvalidation.Jornada.Domain.Entities.Jornada;
import app.block5crudvalidation.Jornada.Infraestructure.DTO.JornadaInputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JornadaInputMapper {
//    @Mapping(source = "id", target = "id")
//    @Mapping(source = "numeroJornada", target = "numeroJornada")
//    @Mapping(source = "fecha", target = "fecha")
    JornadaInputDTO InputJornadaToJornadaDto(Jornada jornada);
    Jornada InputJornadaDtoToJornada(JornadaInputDTO jornadaDto);
}
