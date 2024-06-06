package app.block5crudvalidation.Jornada.Infraestructure.Controller;


import app.block5crudvalidation.Jornada.Application.Services.JornadaService;
import app.block5crudvalidation.Jornada.Domain.Entities.Jornada;
import app.block5crudvalidation.Jornada.Domain.Mapper.JornadaInputMapper;
import app.block5crudvalidation.Jornada.Domain.Mapper.JornadaOutputMapper;
import app.block5crudvalidation.Jornada.Infraestructure.DTO.JornadaInputDTO;
import app.block5crudvalidation.Jornada.Infraestructure.DTO.JornadaOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jornadas")
public class JornadaController {

    private final JornadaInputMapper jornadaInputMapper;
    private final JornadaOutputMapper jornadaOutputMapper;
    private final JornadaService jornadaService;

    @GetMapping
    public ResponseEntity<?> getAllJornadas() {
        List<Jornada> result = jornadaService.findAll();
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<JornadaOutputDTO> dtoList = result.stream()
                    .map(jornadaOutputMapper::OutputJornadaToJornadaDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(dtoList);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<JornadaOutputDTO> getJornadaById(@PathVariable Long id) {
        Jornada jornada = jornadaService.findById(id);
        return ResponseEntity.ok(jornadaOutputMapper.OutputJornadaToJornadaDto(jornada));
    }

    @PostMapping
    public List<JornadaInputDTO> createAll(@RequestBody List<Jornada> jornadas) {
        jornadaService.saveAll(jornadas);
        return jornadas.stream()
                .map(jornadaInputMapper::InputJornadaToJornadaDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jornada> updateJornada(@PathVariable Long id, @RequestBody Jornada jornadaDetails) {
        Jornada jornada = jornadaService.findById(id);
        jornada.setNumeroJornada(jornadaDetails.getNumeroJornada());
        jornada.setFecha(jornadaDetails.getFecha());

        Jornada updatedJornada = jornadaService.save(jornada);
        return ResponseEntity.ok(updatedJornada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Jornada> deleteJornada(@PathVariable Long id) {
        Jornada jornada = jornadaService.findById(id);
        jornadaService.deleteById(id);
        return ResponseEntity.ok(jornada);
    }
}
