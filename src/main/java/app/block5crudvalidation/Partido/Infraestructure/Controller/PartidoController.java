package app.block5crudvalidation.Partido.Infraestructure.Controller;


import app.block5crudvalidation.Partido.Application.Services.PartidoService;
import app.block5crudvalidation.Partido.Domain.Entities.Partido;
import app.block5crudvalidation.Partido.Domain.Mapper.PartidoInputMapper;
import app.block5crudvalidation.Partido.Domain.Mapper.PartidoOutputMapper;
import app.block5crudvalidation.Partido.Infraestructure.DTO.PartidoInputDTO;
import app.block5crudvalidation.Partido.Infraestructure.DTO.PartidoOutputDTO;
import app.block5crudvalidation.Partido.Infraestructure.DTO.PartidoOutputDTO2;
import app.block5crudvalidation.Partido.Infraestructure.Repository.PartidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/partidos")
public class PartidoController {

    private final PartidoInputMapper partidoInputMapper;
    private final PartidoOutputMapper partidoOutputMapper;
    private final PartidoService partidoService;
    private final PartidoRepository partidoRepository;


    @GetMapping
    public ResponseEntity<?> getAllPartidos() {
        List<Partido> result = partidoService.findAll();
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<PartidoOutputDTO> dtoList = result.stream()
                    .map(partidoOutputMapper::OutputPartidoToPartidoDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(dtoList);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartidoOutputDTO> getPartidoById(@PathVariable Long id) {
        Partido partido = partidoService.findById(id);
        return ResponseEntity.ok(partidoOutputMapper.OutputPartidoToPartidoDto(partido));
    }

    @GetMapping("/api/partidos")
    public ResponseEntity<List<PartidoOutputDTO2>> getPartidos(@RequestParam("jornadaId") Long jornadaId) {
        List<Partido> result = partidoRepository.findByJornadaId(jornadaId);

        List<PartidoOutputDTO2> dtoList = result.stream()
                .map(partidoOutputMapper::OutputPartidoToPartidoDto2)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    @PostMapping
    public List<PartidoInputDTO> createAll(@RequestBody List<Partido> partidos) {
        partidoService.saveAll(partidos);
        return partidos.stream()
                .map(partidoInputMapper::InputPartidoToPartidoDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Partido> updatePartido(@PathVariable Long id, @RequestBody Partido partidoDetails) {
        Partido partido = partidoService.findById(id);
        partido.setJornada(partidoDetails.getJornada());
        partido.setEquipoLocal(partidoDetails.getEquipoLocal());
        partido.setEquipoVisitante(partidoDetails.getEquipoVisitante());
        partido.setGolesLocal(partidoDetails.getGolesLocal());
        partido.setGolesVisitante(partidoDetails.getGolesVisitante());
        partido.setFechaHora(partidoDetails.getFechaHora());

        Partido updatedPartido = partidoService.save(partido);
        return ResponseEntity.ok(updatedPartido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Partido> deletePartido(@PathVariable Long id) {
        Partido partido = partidoService.findById(id);
        partidoService.deleteById(id);
        return ResponseEntity.ok(partido);
    }
}
