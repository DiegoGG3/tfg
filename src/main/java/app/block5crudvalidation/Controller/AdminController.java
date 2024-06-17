package app.block5crudvalidation.Controller;

import app.block5crudvalidation.User.Domain.Entities.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String home() {
        return "adminView"; // Esto devolverá el archivo src/main/resources/templates/index.html
    }

}
