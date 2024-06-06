package app.block5crudvalidation.Equipo.Domain.Mapper;


import app.block5crudvalidation.Equipo.Domain.Entities.Equipo;
import app.block5crudvalidation.Equipo.Infraestructure.DTO.EquipoOutputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EquipoOutputMapper {
//    @Mapping(source = "id", target = "id")
//    @Mapping(source = "nombre", target = "nombre")
//    @Mapping(source = "anoFundacion", target = "anoFundacion")
//    @Mapping(source = "presidente", target = "presidente")
//    @Mapping(source = "numeroPremios", target = "numeroPremios")
//    @Mapping(source = "fotoEscudo", target = "fotoEscudo")
//    @Mapping(source = "estadio", target = "estadio")
    EquipoOutputDTO OutputEquipoToEquipoDto(Equipo equipo);

    Equipo OutputEquipoDtoToEquipo(EquipoOutputDTO equipoDto);
}
