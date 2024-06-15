package app.block5crudvalidation.Controller;

import app.block5crudvalidation.Partido.Application.Services.PartidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/partidos")
public class JugarPartidoController {

    @Autowired
    private PartidoService partidoService;

    @PostMapping("/finalizar")
    public ResponseEntity<?> finalizarPartido(@RequestParam Long partidoId) {
        try {
            partidoService.finalizarPartido(partidoId);
            return ResponseEntity.ok().body(Map.of("success", true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("success", false, "message", e.getMessage()));
        }
    }
}
