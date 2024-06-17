package app.block5crudvalidation.User.Application.Services;

import app.block5crudvalidation.Config.security.UserRegisterDTO;
import app.block5crudvalidation.User.Domain.Entities.User;
import app.block5crudvalidation.User.Infraestructure.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User findById(Long id) {
        return userRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new EntityNotFoundException("User no encontrado con id: " + id));
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
    public Optional<User> loginUser(String gmail, String contrasena) {
        Optional<User> user = userRepository.findByGmail(gmail);
        if (user.isPresent() && passwordEncoder.matches(contrasena, user.get().getContrasena())) {
            return user;
        }
        return Optional.empty();
    }

    @Override
    public User register(UserRegisterDTO userRegisterDTO) {
        User user = new User();
        user.setNombre(userRegisterDTO.getNombre());
        user.setApellido(userRegisterDTO.getApellido());
        user.setGmail(userRegisterDTO.getGmail());
        user.setContrasena(userRegisterDTO.getContrasena());

        User savedUser = userRepository.save(user);
        System.out.println("Usuario guardado: " + savedUser);
        return savedUser;
    }

    @Override
    public Optional<User> findByGmail(String gmail) {
        return userRepository.findByGmail(gmail);
    }
}
