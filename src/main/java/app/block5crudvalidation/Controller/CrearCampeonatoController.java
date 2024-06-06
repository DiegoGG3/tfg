package app.block5crudvalidation.Controller;


import app.block5crudvalidation.Campeonato.Application.Services.CampeonatoService;
import app.block5crudvalidation.Campeonato.Domain.Entities.Campeonato;
import app.block5crudvalidation.Equipo.Application.Services.EquipoService;
import app.block5crudvalidation.Jugador.Application.Services.JugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CrearCampeonatoController {

    @Autowired
    private EquipoService equipoService;

    @GetMapping("/crearcampeonatos/create")
    public String showCreateForm(Model model) {
        model.addAttribute("equipos", equipoService.findAll());
        return "crear-campeonato";
    }

}
