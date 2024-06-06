package app.block5crudvalidation.Equipo.Infraestructure.Controller;


import app.block5crudvalidation.Equipo.Application.Services.EquipoService;
import app.block5crudvalidation.Equipo.Domain.Entities.Equipo;
import app.block5crudvalidation.Equipo.Domain.Mapper.EquipoInputMapper;
import app.block5crudvalidation.Equipo.Domain.Mapper.EquipoOutputMapper;
import app.block5crudvalidation.Equipo.Infraestructure.DTO.EquipoInputDTO;
import app.block5crudvalidation.Equipo.Infraestructure.DTO.EquipoOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/equipos")
public class EquipoController {

    private final EquipoInputMapper equipoInputMapper;
    private final EquipoOutputMapper equipoOutputMapper;
    private final EquipoService equipoService;

    @GetMapping
    public ResponseEntity<?> getAllEquipos() {
        List<Equipo> result = equipoService.findAll();
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<EquipoOutputDTO> dtoList = result.stream()
                    .map(equipoOutputMapper::OutputEquipoToEquipoDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(dtoList);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipoOutputDTO> getEquipoById(@PathVariable Long id) {
        Equipo equipo = equipoService.findById(id);
        return ResponseEntity.ok(equipoOutputMapper.OutputEquipoToEquipoDto(equipo));
    }

    @PostMapping
    public List<EquipoInputDTO> createAll(@RequestBody List<Equipo> equipos) {
        equipoService.saveAll(equipos);
        return equipos.stream()
                .map(equipoInputMapper::InputEquipoToEquipoDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipo> updateEquipo(@PathVariable Long id, @RequestBody Equipo equipoDetails) {
        Equipo equipo = equipoService.findById(id);
        equipo.setNombre(equipoDetails.getNombre());
        equipo.setAnoFundacion(equipoDetails.getAnoFundacion());
        equipo.setPresidente(equipoDetails.getPresidente());
        equipo.setNumeroPremios(equipoDetails.getNumeroPremios());
        equipo.setFotoEscudo(equipoDetails.getFotoEscudo());
        equipo.setEstadio(equipoDetails.getEstadio());

        Equipo updatedEquipo = equipoService.save(equipo);
        return ResponseEntity.ok(updatedEquipo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Equipo> deleteEquipo(@PathVariable Long id) {
        Equipo equipo = equipoService.findById(id);
        equipoService.deleteById(id);
        return ResponseEntity.ok(equipo);
    }
}
