package app.block5crudvalidation.Controller;

import app.block5crudvalidation.Jugador.Domain.Entities.Jugador;
import app.block5crudvalidation.Jugador.Infraestructure.Repository.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class VerJugadores {

    @Autowired
    private JugadorRepository jugadorRepository;

    @GetMapping("/verjugadores")
    public String mostrarJugadores(Model model) {
        List<Jugador> jugadores = jugadorRepository.findAll();

        List<Jugador> jugadoresOrdenados = jugadores.stream()
                .sorted(Comparator.comparing(Jugador::getNombre))
                .collect(Collectors.toList());

        model.addAttribute("jugadores", jugadoresOrdenados);

        return "ver-jugadores";
    }

    @GetMapping("/verjugadoresadmin")
    public String mostrarJugadoresAdmin(Model model) {
        List<Jugador> jugadores = jugadorRepository.findAll();

        List<Jugador> jugadoresOrdenados = jugadores.stream()
                .sorted(Comparator.comparing(Jugador::getNombre))
                .collect(Collectors.toList());

        model.addAttribute("jugadores", jugadoresOrdenados);

        return "ver-jugadores-admin";
    }
}
