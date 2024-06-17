package app.block5crudvalidation.Controller;

import app.block5crudvalidation.Config.security.UserLoginDTO;
import app.block5crudvalidation.User.Application.Services.UserService;
import app.block5crudvalidation.User.Domain.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("userLoginDTO", new UserLoginDTO());
        return "login";
    }

    @GetMapping("/api/users")
    @ResponseBody
    public List<User> getAllUsers() {
        return userService.findAll();
    }
}
