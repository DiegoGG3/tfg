package app.block5crudvalidation.JugadorPartido.Domain.Mapper;


import app.block5crudvalidation.JugadorPartido.Domain.Entities.JugadorPartido;
import app.block5crudvalidation.JugadorPartido.Infraestructure.DTO.JugadorPartidoInputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JugadorPartidoInputMapper {
//    @Mapping(source = "id", target = "id")
//    @Mapping(source = "partido.id", target = "partidoId")
//    @Mapping(source = "jugador.id", target = "jugadorId")
//    @Mapping(source = "minutoEntra", target = "minutoEntra")
//    @Mapping(source = "minutoSale", target = "minutoSale")
    JugadorPartidoInputDTO InputJugadorPartidoToJugadorPartidoDto(JugadorPartido jugadorPartido);
    JugadorPartido InputJugadorPartidoDtoToJugadorPartido(JugadorPartidoInputDTO jugadorPartidoDto);
}
