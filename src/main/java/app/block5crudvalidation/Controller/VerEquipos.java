package app.block5crudvalidation.Controller;

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

    @GetMapping("/verequipos")
    public String showEquipos(Model model) {
        List<Equipo> equipos = equipoRepository.findAll();
        model.addAttribute("equipos", equipos);
        return "ver-equipos";
    }
}
