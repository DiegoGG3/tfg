package app.block5crudvalidation.Controller;

import app.block5crudvalidation.Equipo.Application.Services.EquipoService;
import app.block5crudvalidation.Equipo.Domain.Entities.Equipo;
import app.block5crudvalidation.Equipo.Infraestructure.Repository.EquipoRepository;
import app.block5crudvalidation.Jugador.Domain.Entities.Jugador;
import app.block5crudvalidation.Jugador.Infraestructure.Repository.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class VerEquipos {

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private JugadorRepository jugadorRepository;

    @Autowired
    private EquipoService equipoService;

    @GetMapping("/verequipos")
    public String showEquipos(Model model) {
        List<Equipo> equipos = equipoRepository.findAll();
        model.addAttribute("equipos", equipos);
        return "ver-equipos";
    }
    @GetMapping("/equipos/{equipoId}/jugadores")
    public String listJugadores(@PathVariable int equipoId, Model model) {
        List<Jugador> jugadores = equipoService.findJugadoresByEquipoId(equipoId);
        model.addAttribute("jugadores", jugadores);
        return "jugadores";
    }

    @GetMapping("/verequiposadmin")
    public String showEquiposAdmin(Model model) {
        List<Equipo> equipos = equipoRepository.findAll();
        model.addAttribute("equipos", equipos);
        return "ver-equipos-admin";
    }
}
