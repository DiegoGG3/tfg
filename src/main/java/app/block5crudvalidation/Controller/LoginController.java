package app.block5crudvalidation.Controller;
import app.block5crudvalidation.Config.security.UserLoginDTO;
import app.block5crudvalidation.User.Application.Services.UserService;
import app.block5crudvalidation.User.Domain.Entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // nombre del template HTML donde está el formulario de login
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
        return "redirect:/login?error";  // Redirigir a la página de login con error
    }
}
