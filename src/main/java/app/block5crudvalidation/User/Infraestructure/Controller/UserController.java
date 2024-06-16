package app.block5crudvalidation.User.Infraestructure.Controller;


import app.block5crudvalidation.Config.security.UserLoginDTO;
import app.block5crudvalidation.Config.security.UserRegisterDTO;
import app.block5crudvalidation.User.Application.Services.UserService;
import app.block5crudvalidation.User.Domain.Entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userRegisterDTO", new UserRegisterDTO());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserRegisterDTO userRegisterDTO) {
        userService.register(userRegisterDTO);
        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("userLoginDTO", new UserLoginDTO());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute UserLoginDTO userLoginDTO, Model model) {
        Optional<User> user = userService.findByGmail(userLoginDTO.getGmail());
        if (user.isPresent()) {
            if (passwordEncoder.matches(userLoginDTO.getContrasena(), user.get().getContrasena())) {
                model.addAttribute("user", user.get());
                return "redirect:/admin";  // Redirigir a una página de éxito
            } else {
                System.out.println("Contraseña incorrecta para el usuario: " + userLoginDTO.getGmail());
            }
        } else {
            System.out.println("Usuario no encontrado con gmail: " + userLoginDTO.getGmail());
        }
        return "redirect:/users/login?error";  // Redirigir a la página de login con error
    }

}
