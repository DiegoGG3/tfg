package app.block5crudvalidation.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String home() {
        return "adminView"; // Esto devolverá el archivo src/main/resources/templates/index.html
    }
}
