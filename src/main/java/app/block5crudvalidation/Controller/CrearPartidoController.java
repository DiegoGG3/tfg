package app.block5crudvalidation.Controller;

import app.block5crudvalidation.Equipo.Domain.Entities.Equipo;
import app.block5crudvalidation.Jornada.Domain.Entities.Jornada;
import app.block5crudvalidation.Partido.Application.Services.PartidoService;
import app.block5crudvalidation.Partido.Domain.Entities.Partido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/crearpartidos")
public class CrearPartidoController {

    @Autowired
    private PartidoService partidoService;

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        List<Equipo> equipos = partidoService.getAllEquipos();
        List<Jornada> jornadas = partidoService.getAllJornadas();
        model.addAttribute("partido", new Partido());
        model.addAttribute("equipos", equipos);
        model.addAttribute("jornadas", jornadas);
        return "crear-partido";
    }

}
