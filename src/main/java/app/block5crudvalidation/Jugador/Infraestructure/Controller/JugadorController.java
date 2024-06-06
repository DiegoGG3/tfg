package app.block5crudvalidation.Jugador.Infraestructure.Controller;


import app.block5crudvalidation.Jugador.Application.Services.JugadorService;
import app.block5crudvalidation.Jugador.Domain.Entities.Jugador;
import app.block5crudvalidation.Jugador.Domain.Mapper.JugadorInputMapper;
import app.block5crudvalidation.Jugador.Domain.Mapper.JugadorOutputMapper;
import app.block5crudvalidation.Jugador.Infraestructure.DTO.JugadorInputDTO;
import app.block5crudvalidation.Jugador.Infraestructure.DTO.JugadorOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jugadores")
public class JugadorController {

    private final JugadorInputMapper jugadorInputMapper;
    private final JugadorOutputMapper jugadorOutputMapper;
    private final JugadorService jugadorService;

    @GetMapping
    public ResponseEntity<?> getAllJugadores() {
        List<Jugador> result = jugadorService.findAll();
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<JugadorOutputDTO> dtoList = result.stream()
                    .map(jugadorOutputMapper::OutputJugadorToJugadorDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(dtoList);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<JugadorOutputDTO> getJugadorById(@PathVariable Long id) {
        Jugador jugador = jugadorService.findById(id);
        return ResponseEntity.ok(jugadorOutputMapper.OutputJugadorToJugadorDto(jugador));
    }

    @PostMapping
    public List<JugadorInputDTO> createAll(@RequestBody List<Jugador> jugadores) {
        jugadorService.saveAll(jugadores);
        return jugadores.stream()
                .map(jugadorInputMapper::InputJugadorToJugadorDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jugador> updateJugador(@PathVariable Long id, @RequestBody Jugador jugadorDetails) {
        Jugador jugador = jugadorService.findById(id);
        jugador.setNombre(jugadorDetails.getNombre());
        jugador.setApellido1(jugadorDetails.getApellido1());
        jugador.setApellido2(jugadorDetails.getApellido2());
        jugador.setFechaNacimiento(jugadorDetails.getFechaNacimiento());
        jugador.setGolesTotales(jugadorDetails.getGolesTotales());
        jugador.setAsistenciasTotales(jugadorDetails.getAsistenciasTotales());
        jugador.setNacionalidad(jugadorDetails.getNacionalidad());
        jugador.setEquipo(jugadorDetails.getEquipo());

        Jugador updatedJugador = jugadorService.save(jugador);
        return ResponseEntity.ok(updatedJugador);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Jugador> deleteJugador(@PathVariable Long id) {
        Jugador jugador = jugadorService.findById(id);
        jugadorService.deleteById(id);
        return ResponseEntity.ok(jugador);
    }
}
