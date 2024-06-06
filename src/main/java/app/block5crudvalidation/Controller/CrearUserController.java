package app.block5crudvalidation.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CrearUserController {

    @GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        return "crear-user";
    }
}