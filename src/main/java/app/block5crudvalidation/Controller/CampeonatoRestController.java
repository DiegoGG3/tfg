package app.block5crudvalidation.Controller;

import app.block5crudvalidation.Campeonato.Domain.Entities.Campeonato;
import app.block5crudvalidation.Campeonato.Infraestructure.Repository.CampeonatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CampeonatoRestController {

    @Autowired
    private CampeonatoRepository campeonatoRepository;

    @GetMapping("/api/campeonatos/{id}")
    public Campeonato getCampeonatoById(@PathVariable int id) {
        Optional<Campeonato> campeonato = campeonatoRepository.findById(id);
        return campeonato.orElse(null);
    }
}
