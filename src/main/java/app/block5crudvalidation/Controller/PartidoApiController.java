package app.block5crudvalidation.Controller;
import app.block5crudvalidation.Equipo.Application.Services.EquipoService;
import app.block5crudvalidation.Jugador.Domain.Entities.Jugador;
import app.block5crudvalidation.Partido.Application.Services.PartidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partidos")
public class PartidoApiController {

    @Autowired
    private PartidoService partidoService;

    @Autowired
    private EquipoService equipoService;

    @PostMapping("/goal")
    public ResponseEntity<?> addGoal(@RequestParam int partidoId, @RequestParam int jugadorId, @RequestParam String equipoTipo) {
        partidoService.addGoal(partidoId, jugadorId, equipoTipo);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/assist")
    public ResponseEntity<?> addAssist(@RequestParam int partidoId, @RequestParam int jugadorId, @RequestParam String equipoTipo) {
        partidoService.addAssist(partidoId, jugadorId, equipoTipo);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/equipos/{equipoTipo}/{partidoId}/jugadores")
    public ResponseEntity<List<Jugador>> getJugadoresByEquipo(@PathVariable String equipoTipo, @PathVariable int partidoId) {
        List<Jugador> jugadores = equipoService.getJugadoresByEquipo(partidoId, equipoTipo);
        return ResponseEntity.ok(jugadores);
    }
}
