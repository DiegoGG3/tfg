package app.block5crudvalidation.Controller;


import app.block5crudvalidation.Campeonato.Domain.Entities.Campeonato;
import app.block5crudvalidation.Campeonato.Infraestructure.Repository.CampeonatoRepository;
import app.block5crudvalidation.Jornada.Domain.Entities.Jornada;
import app.block5crudvalidation.Jornada.Infraestructure.Repository.JornadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class VerClasificacion {

    @Autowired
    private CampeonatoRepository campeonatoRepository;

    @Autowired
    private JornadaRepository jornadaRepository;

    @GetMapping("/vercampeonatosadmin")
    public String showCampeonatos(Model model) {
        List<Campeonato> campeonatos = campeonatoRepository.findAll();
        model.addAttribute("campeonatos", campeonatos);
        return "ver-clasificacion";
    }

    @GetMapping("/clasificacion")
    public String showClasificacion(@RequestParam("campeonatoId") Long campeonatoId, Model model) {
        Campeonato campeonato = campeonatoRepository.findById(Math.toIntExact(campeonatoId)).orElse(null);
        List<Jornada> jornadas = jornadaRepository.findByCampeonatoId(campeonatoId);
        model.addAttribute("campeonato", campeonato);
        model.addAttribute("jornadas", jornadas);
        return "verjornadas";
    }

}
