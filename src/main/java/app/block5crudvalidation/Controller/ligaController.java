package app.block5crudvalidation.Controller;

import app.block5crudvalidation.CampeonatoEquipo.Domain.Entities.CampeonatoEquipo;
import app.block5crudvalidation.CampeonatoEquipo.Infraestructure.Repository.CampeonatoEquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ligaController {

    @Autowired
    private CampeonatoEquipoRepository campeonatoEquipoRepository;

    @GetMapping("/")
    public String showLiga(Model model) {
        List<CampeonatoEquipo> equipos = campeonatoEquipoRepository.findByCampeonatoId(1);
        model.addAttribute("equipos", equipos);
        return "index";
    }
}
