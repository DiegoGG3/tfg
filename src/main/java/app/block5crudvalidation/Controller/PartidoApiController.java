package app.block5crudvalidation.Controller;

import app.block5crudvalidation.Equipo.Application.Services.EquipoService;
import app.block5crudvalidation.Incidencias.Application.Services.IncidenciasService;
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

    @Autowired
    private IncidenciasService incidenciasService;

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

    @PostMapping("/goalAssist")
    public ResponseEntity<Void> addGoalAssist(@RequestParam int partidoId,
                                              @RequestParam String equipoTipo,
                                              @RequestParam int jugadorIdGol,
                                              @RequestParam int jugadorIdAsistencia) {
        partidoService.addGoal(partidoId, jugadorIdGol, equipoTipo);
        partidoService.addAssist(partidoId, jugadorIdAsistencia, equipoTipo);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/incidencia")
    public ResponseEntity<Void> addIncidencia(@RequestParam int partidoId,
                                              @RequestParam int jugadorId,
                                              @RequestParam int minuto,
                                              @RequestParam String tipo,
                                              @RequestParam String descripcion) {
        incidenciasService.addIncidencia(partidoId, jugadorId, minuto, tipo, descripcion);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{partidoId}/jugadores")
    public ResponseEntity<List<Jugador>> getJugadoresByPartido(@PathVariable int partidoId) {
        List<Jugador> jugadores = partidoService.getJugadoresByPartido(partidoId);
        return ResponseEntity.ok(jugadores);
    }

}
