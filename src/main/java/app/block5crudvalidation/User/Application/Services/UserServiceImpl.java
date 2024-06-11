package app.block5crudvalidation.User.Application.Services;

import app.block5crudvalidation.User.Domain.Entities.User;
import app.block5crudvalidation.User.Domain.Mapper.UserInputMapper;
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
    private final UserInputMapper userMapper;
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
    public User registerUser(User user) {
        user.setContrasena(passwordEncoder.encode(user.getContrasena()));
        return userRepository.save(user);
    }
}
