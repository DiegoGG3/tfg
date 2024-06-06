package app.block5crudvalidation.User.Infraestructure.Controller;


import app.block5crudvalidation.User.Application.Services.UserService;
import app.block5crudvalidation.User.Domain.Entities.User;
import app.block5crudvalidation.User.Domain.Mapper.UserInputMapper;
import app.block5crudvalidation.User.Domain.Mapper.UserOutputMapper;
import app.block5crudvalidation.User.Infraestructure.DTO.UserInputDTO;
import app.block5crudvalidation.User.Infraestructure.DTO.UserOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserInputMapper userInputMapper;
    private final UserOutputMapper userOutputMapper;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<User> result = userService.findAll();
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<UserOutputDTO> dtoList = result.stream()
                    .map(userOutputMapper::OutputUserToUserDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(dtoList);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserOutputDTO> getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(userOutputMapper.OutputUserToUserDto(user));
    }

    @PostMapping
    public List<UserInputDTO> createAll(@RequestBody List<User> users) {
        userService.saveAll(users);
        return users.stream()
                .map(userInputMapper::InputUserToUserDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User user = userService.findById(id);
        user.setNombre(userDetails.getNombre());
        user.setApellido(userDetails.getApellido());
        user.setGmail(userDetails.getGmail());
        user.setContrasena(userDetails.getContrasena());
        user.setRol(userDetails.getRol());

        User updatedUser = userService.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        User user = userService.findById(id);
        userService.deleteById(id);
        return ResponseEntity.ok(user);
    }
}
