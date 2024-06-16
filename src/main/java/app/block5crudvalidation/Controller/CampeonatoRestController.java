package app.block5crudvalidation.Controller;

import app.block5crudvalidation.Campeonato.Application.Services.CampeonatoService;
import app.block5crudvalidation.Campeonato.Domain.Entities.Campeonato;
import app.block5crudvalidation.Campeonato.Infraestructure.Repository.CampeonatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CampeonatoRestController {

    @Autowired
    private CampeonatoRepository campeonatoRepository;

    @Autowired
    private CampeonatoService campeonatoService;


    @GetMapping("/api/campeonatos/{id}")
    public Campeonato getCampeonatoById(@PathVariable int id) {
        Optional<Campeonato> campeonato = campeonatoRepository.findById(id);
        return campeonato.orElse(null);
    }

    @PostMapping("/finalizar/{campeonatoId}")
    public ResponseEntity<?> finalizarCampeonato(@PathVariable Long campeonatoId) {
        try {
            Campeonato campeonato = campeonatoService.finalizarCampeonato(campeonatoId);
            return ResponseEntity.ok(("Campeonato finalizado. Ganador: " + campeonato.getGanador()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(("Error al finalizar el campeonato: " + e.getMessage()));
        }
    }
}
