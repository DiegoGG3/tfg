package app.block5crudvalidation.Controller;


import app.block5crudvalidation.Equipo.Application.Services.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class CrearEquipoController {

    @Autowired
    private EquipoService equipoService;

    @GetMapping("/crearequipos/create")
    public String showCreateForm(Model model) {
        return "crear-equipo";
    }

}
