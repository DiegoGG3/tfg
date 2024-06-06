package app.block5crudvalidation.User.Domain.Mapper;


import app.block5crudvalidation.User.Domain.Entities.User;
import app.block5crudvalidation.User.Infraestructure.DTO.UserInputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserInputMapper {
//    @Mapping(source = "id", target = "id")
//    @Mapping(source = "nombre", target = "nombre")
//    @Mapping(source = "apellido", target = "apellido")
//    @Mapping(source = "gmail", target = "gmail")
//    @Mapping(source = "contrasena", target = "contrasena")
//    @Mapping(source = "rol", target = "rol")
    UserInputDTO InputUserToUserDto(User user);
    User InputUserDtoToUser(UserInputDTO userDto);
}
