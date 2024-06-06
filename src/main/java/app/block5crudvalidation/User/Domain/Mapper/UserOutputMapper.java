package app.block5crudvalidation.User.Domain.Mapper;


import app.block5crudvalidation.User.Domain.Entities.User;
import app.block5crudvalidation.User.Infraestructure.DTO.UserOutputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserOutputMapper {
//    @Mapping(source = "id", target = "id")
//    @Mapping(source = "nombre", target = "nombre")
//    @Mapping(source = "apellido", target = "apellido")
//    @Mapping(source = "gmail", target = "gmail")
//    @Mapping(source = "contrasena", target = "contrasena")
//    @Mapping(source = "rol", target = "rol")
    UserOutputDTO OutputUserToUserDto(User user);

    User OutputUserDtoToUser(UserOutputDTO userDto);
}
