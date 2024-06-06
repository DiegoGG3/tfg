package app.block5crudvalidation.User.Application.Services;


import app.block5crudvalidation.User.Domain.Entities.User;
import app.block5crudvalidation.User.Domain.Mapper.UserInputMapper;
import app.block5crudvalidation.User.Infraestructure.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserInputMapper userMapper;

    @Override
    public User findById(Long id) {
        return userRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new EntityNotFoundException("User no encontrado con id: " + id));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        if (!userRepository.existsById(Math.toIntExact(id))) {
            throw new EntityNotFoundException("No se puede eliminar, User no encontrado con id: " + id);
        }
        userRepository.deleteById(Math.toIntExact(id));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void saveAll(List<User> users) {
        userRepository.saveAll(users);
    }

}
