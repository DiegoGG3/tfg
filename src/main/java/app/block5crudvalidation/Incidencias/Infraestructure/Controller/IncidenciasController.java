package app.block5crudvalidation.Incidencias.Infraestructure.Controller;



import app.block5crudvalidation.Incidencias.Application.Services.IncidenciasService;
import app.block5crudvalidation.Incidencias.Domain.Entities.Incidencias;
import app.block5crudvalidation.Incidencias.Domain.Mapper.IncidenciasInputMapper;
import app.block5crudvalidation.Incidencias.Domain.Mapper.IncidenciasOutputMapper;
import app.block5crudvalidation.Incidencias.Infraestructure.DTO.IncidenciasInputDTO;
import app.block5crudvalidation.Incidencias.Infraestructure.DTO.IncidenciasOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/incidencias")
public class IncidenciasController {

    private final IncidenciasInputMapper incidenciasInputMapper;
    private final IncidenciasOutputMapper incidenciasOutputMapper;
    private final IncidenciasService incidenciasService;

    @GetMapping
    public ResponseEntity<?> getAllIncidencias() {
        List<Incidencias> result = incidenciasService.findAll();
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<IncidenciasOutputDTO> dtoList = result.stream()
                    .map(incidenciasOutputMapper::OutputIncidenciasToIncidenciasDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(dtoList);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncidenciasOutputDTO> getIncidenciasById(@PathVariable Long id) {
        Incidencias incidencias = incidenciasService.findById(id);
        return ResponseEntity.ok(incidenciasOutputMapper.OutputIncidenciasToIncidenciasDto(incidencias));
    }

    @PostMapping
    public List<IncidenciasInputDTO> createAll(@RequestBody List<Incidencias> incidencias) {
        incidenciasService.saveAll(incidencias);
        return incidencias.stream()
                .map(incidenciasInputMapper::InputIncidenciasToIncidenciasDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Incidencias> updateIncidencias(@PathVariable Long id, @RequestBody Incidencias incidenciasDetails) {
        Incidencias incidencias = incidenciasService.findById(id);
        incidencias.setPartido(incidenciasDetails.getPartido());
        incidencias.setJugador(incidenciasDetails.getJugador());
        incidencias.setTipo(incidenciasDetails.getTipo());
        incidencias.setMinuto(incidenciasDetails.getMinuto());
        incidencias.setDescripcion(incidenciasDetails.getDescripcion());

        Incidencias updatedIncidencias = incidenciasService.save(incidencias);
        return ResponseEntity.ok(updatedIncidencias);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Incidencias> deleteIncidencias(@PathVariable Long id) {
        Incidencias incidencias = incidenciasService.findById(id);
        incidenciasService.deleteById(id);
        return ResponseEntity.ok(incidencias);
    }
}
