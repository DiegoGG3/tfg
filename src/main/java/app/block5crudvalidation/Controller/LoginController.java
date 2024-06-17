package app.block5crudvalidation.Controller;

import app.block5crudvalidation.User.Application.Services.UserService;
import app.block5crudvalidation.User.Domain.Entities.User;
import app.block5crudvalidation.Config.security.UserLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import org.springframework.http.ResponseEntity;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;


    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("userLoginDTO", new UserLoginDTO());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute UserLoginDTO userLoginDTO, Model model) {
        Optional<User> user = userService.loginUser(userLoginDTO.getGmail(), userLoginDTO.getContrasena());
        if (user.isPresent()) {
            return "redirect:/admin";
        } else {
            model.addAttribute("loginError", "Correo o contraseña incorrectos");
            return "login";
        }
    }
}
