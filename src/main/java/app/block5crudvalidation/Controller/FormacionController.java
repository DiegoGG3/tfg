package app.block5crudvalidation.Controller;

import app.block5crudvalidation.Equipo.Application.Services.EquipoService;
import app.block5crudvalidation.Jugador.Domain.Entities.Jugador;
import app.block5crudvalidation.Partido.Application.Services.PartidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class FormacionController {

    @Autowired
    private PartidoService partidoService;

    @Autowired
    private EquipoService equipoService;

    @GetMapping("/formaciones/{partidoId}")
    public String verFormaciones(@PathVariable int partidoId, Model model) {
        List<Jugador> equipo1Jugadores = equipoService.getJugadoresByEquipo(partidoId, "local");
        List<Jugador> equipo2Jugadores = equipoService.getJugadoresByEquipo(partidoId, "visitante");

        model.addAttribute("equipo1Jugadores", equipo1Jugadores);
        model.addAttribute("equipo2Jugadores", equipo2Jugadores);

        return "formaciones";
    }
}
