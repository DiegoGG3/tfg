package app.block5crudvalidation.JugadorPartido.Infraestructure.Controller;


import app.block5crudvalidation.JugadorPartido.Application.Services.JugadorPartidoService;
import app.block5crudvalidation.JugadorPartido.Domain.Entities.JugadorPartido;
import app.block5crudvalidation.JugadorPartido.Domain.Mapper.JugadorPartidoInputMapper;
import app.block5crudvalidation.JugadorPartido.Domain.Mapper.JugadorPartidoOutputMapper;
import app.block5crudvalidation.JugadorPartido.Infraestructure.DTO.JugadorPartidoInputDTO;
import app.block5crudvalidation.JugadorPartido.Infraestructure.DTO.JugadorPartidoOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jugador-partidos")
public class JugadorPartidoController {

    private final JugadorPartidoInputMapper jugadorPartidoInputMapper;
    private final JugadorPartidoOutputMapper jugadorPartidoOutputMapper;
    private final JugadorPartidoService jugadorPartidoService;

    @GetMapping
    public ResponseEntity<?> getAllJugadorPartidos() {
        List<JugadorPartido> result = jugadorPartidoService.findAll();
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<JugadorPartidoOutputDTO> dtoList = result.stream()
                    .map(jugadorPartidoOutputMapper::OutputJugadorPartidoToJugadorPartidoDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(dtoList);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<JugadorPartidoOutputDTO> getJugadorPartidoById(@PathVariable Long id) {
        JugadorPartido jugadorPartido = jugadorPartidoService.findById(id);
        return ResponseEntity.ok(jugadorPartidoOutputMapper.OutputJugadorPartidoToJugadorPartidoDto(jugadorPartido));
    }

    @PostMapping
    public ResponseEntity<List<JugadorPartidoInputDTO>> createAll(@RequestBody List<JugadorPartidoInputDTO> jugadorPartidos) {
        List<JugadorPartido> entities = jugadorPartidos.stream()
                .map(jugadorPartidoInputMapper::InputJugadorPartidoDtoToJugadorPartido)
                .collect(Collectors.toList());

        jugadorPartidoService.saveAll(entities);

        return ResponseEntity.ok(jugadorPartidos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JugadorPartido> updateJugadorPartido(@PathVariable Long id, @RequestBody JugadorPartido jugadorPartidoDetails) {
        JugadorPartido jugadorPartido = jugadorPartidoService.findById(id);
        jugadorPartido.setPartido(jugadorPartidoDetails.getPartido());
        jugadorPartido.setJugador(jugadorPartidoDetails.getJugador());
        jugadorPartido.setMinutoEntra(jugadorPartidoDetails.getMinutoEntra());
        jugadorPartido.setMinutoSale(jugadorPartidoDetails.getMinutoSale());

        JugadorPartido updatedJugadorPartido = jugadorPartidoService.save(jugadorPartido);
        return ResponseEntity.ok(updatedJugadorPartido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JugadorPartido> deleteJugadorPartido(@PathVariable Long id) {
        JugadorPartido jugadorPartido = jugadorPartidoService.findById(id);
        jugadorPartidoService.deleteById(id);
        return ResponseEntity.ok(jugadorPartido);
    }
}
