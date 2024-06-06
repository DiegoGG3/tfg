package app.block5crudvalidation.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index"; // Esto devolverá el archivo src/main/resources/templates/index.html
    }
}
