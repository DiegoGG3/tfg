package app.block5crudvalidation.Jugador.Domain.Mapper;


import app.block5crudvalidation.Jugador.Domain.Entities.Jugador;
import app.block5crudvalidation.Jugador.Infraestructure.DTO.JugadorInputDTO;
import app.block5crudvalidation.Jugador.Infraestructure.DTO.JugadorOutputDTOSimple;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JugadorInputMapper {
//    @Mapping(source = "id", target = "id")
//    @Mapping(source = "nombre", target = "nombre")
//    @Mapping(source = "apellido1", target = "apellido1")
//    @Mapping(source = "apellido2", target = "apellido2")
//    @Mapping(source = "fechaNacimiento", target = "fechaNacimiento")
//    @Mapping(source = "golesTotales", target = "golesTotales")
//    @Mapping(source = "asistenciasTotales", target = "asistenciasTotales")
//    @Mapping(source = "nacionalidad", target = "nacionalidad")
//    @Mapping(source = "equipo.id", target = "equipoId")
    JugadorInputDTO InputJugadorToJugadorDto(Jugador jugador);
    Jugador InputJugadorDtoToJugador(JugadorInputDTO jugadorDto);



}
