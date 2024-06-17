package app.block5crudvalidation.User.Application.Services;

import app.block5crudvalidation.Config.security.UserRegisterDTO;
import app.block5crudvalidation.User.Domain.Entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User findById(Long id);


    void deleteById(Long id);

    List<User> findAll();


    Optional<User> loginUser(String gmail, String contrasena);

     User register(UserRegisterDTO userRegisterDTO);

    Optional<User> findByGmail(String gmail);
}
