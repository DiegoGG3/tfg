package app.block5crudvalidation.User.Infraestructure.Controller;

import app.block5crudvalidation.User.Application.Services.UserService;
import app.block5crudvalidation.User.Domain.Entities.User;
import app.block5crudvalidation.User.Domain.Mapper.UserInputMapper;
import app.block5crudvalidation.User.Domain.Mapper.UserOutputMapper;
import app.block5crudvalidation.User.Infraestructure.DTO.UserInputDTO;
import app.block5crudvalidation.User.Infraestructure.DTO.UserOutputDTO;
import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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



    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        User user = userService.findById(id);
        userService.deleteById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    @PostMapping("/loginuser")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session, Model model) {
        Optional<User> authenticatedUser = userService.loginUser(username, password);
        if (authenticatedUser.isPresent()) {
            session.setAttribute("user", authenticatedUser.get());
            return "adminView"; // Devuelve el nombre de la vista
        }
        return "loginPage"; // Devuelve el nombre de la vista de inicio de sesión con el mensaje de error
    }




    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logout successful");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> admin(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null && "ADMIN".equals(user.getRol())) {
            return ResponseEntity.ok("Welcome to the admin page");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }
}
