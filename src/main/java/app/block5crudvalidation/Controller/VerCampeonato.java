package app.block5crudvalidation.Controller;

import app.block5crudvalidation.Campeonato.Domain.Entities.Campeonato;
import app.block5crudvalidation.Campeonato.Infraestructure.Repository.CampeonatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class VerCampeonato {

    @Autowired
    private CampeonatoRepository campeonatoRepository;

    @GetMapping("/vercampeonatos")
    public String showCampeonatos(Model model) {
        List<Campeonato> campeonatos = campeonatoRepository.findAll();
        model.addAttribute("campeonatos", campeonatos);
        return "ver-campeonato";
    }
}
