package app.block5crudvalidation.User.Application.Services;


import app.block5crudvalidation.User.Domain.Entities.User;

import java.util.List;

public interface UserService {

    User findById(Long id);

    User save(User user);

    void deleteById(Long id);

    List<User> findAll();

    void saveAll(List<User> users);

}
