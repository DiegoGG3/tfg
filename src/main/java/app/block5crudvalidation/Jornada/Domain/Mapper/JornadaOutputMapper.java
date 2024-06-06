package app.block5crudvalidation.Jornada.Domain.Mapper;


import app.block5crudvalidation.Jornada.Domain.Entities.Jornada;
import app.block5crudvalidation.Jornada.Infraestructure.DTO.JornadaOutputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JornadaOutputMapper {
//    @Mapping(source = "id", target = "id")
//    @Mapping(source = "numeroJornada", target = "numeroJornada")
//    @Mapping(source = "fecha", target = "fecha")
    JornadaOutputDTO OutputJornadaToJornadaDto(Jornada jornada);

    Jornada OutputJornadaDtoToJornada(JornadaOutputDTO jornadaDto);
}
