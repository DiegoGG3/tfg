package app.block5crudvalidation.Controller;

import app.block5crudvalidation.Equipo.Domain.Entities.Equipo;
import app.block5crudvalidation.Jugador.Domain.Entities.Jugador;
import app.block5crudvalidation.Jugador.Application.Services.JugadorService;
import app.block5crudvalidation.Equipo.Application.Services.EquipoService;

import app.block5crudvalidation.Jugador.Domain.Mapper.JugadorInputMapper;
import app.block5crudvalidation.Jugador.Infraestructure.Controller.JugadorController;
import app.block5crudvalidation.Jugador.Infraestructure.DTO.JugadorInputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/crearjugadores")
public class CrearJugadorController {

    @Autowired
    private JugadorService jugadorService;

    @Autowired
    private EquipoService equipoService;

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("equipos", equipoService.findAll());
        return "crear-jugador";
    }


}
