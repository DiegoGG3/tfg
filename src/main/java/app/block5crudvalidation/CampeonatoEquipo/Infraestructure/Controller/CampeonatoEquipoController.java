package app.block5crudvalidation.CampeonatoEquipo.Infraestructure.Controller;


import app.block5crudvalidation.CampeonatoEquipo.Application.Services.CampeonatoEquipoService;
import app.block5crudvalidation.CampeonatoEquipo.Domain.Entities.CampeonatoEquipo;
import app.block5crudvalidation.CampeonatoEquipo.Domain.Mapper.CampeonatoEquipoInputMapper;
import app.block5crudvalidation.CampeonatoEquipo.Domain.Mapper.CampeonatoEquipoOutputMapper;
import app.block5crudvalidation.CampeonatoEquipo.Infraestructure.DTO.CampeonatoEquipoInputDTO;
import app.block5crudvalidation.CampeonatoEquipo.Infraestructure.DTO.CampeonatoEquipoOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/campeonato-equipos")
public class CampeonatoEquipoController {

    private final CampeonatoEquipoInputMapper campeonatoEquipoInputMapper;
    private final CampeonatoEquipoOutputMapper campeonatoEquipoOutputMapper;
    private final CampeonatoEquipoService campeonatoEquipoService;

    @GetMapping
    public ResponseEntity<?> getAllCampeonatoEquipos() {
        List<CampeonatoEquipo> result = campeonatoEquipoService.findAll();
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<CampeonatoEquipoOutputDTO> dtoList = result.stream()
                    .map(campeonatoEquipoOutputMapper::OutputCampeonatoEquipoToCampeonatoEquipoDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(dtoList);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampeonatoEquipoOutputDTO> getCampeonatoEquipoById(@PathVariable Long id) {
        CampeonatoEquipo campeonatoEquipo = campeonatoEquipoService.findById(id);
        return ResponseEntity.ok(campeonatoEquipoOutputMapper.OutputCampeonatoEquipoToCampeonatoEquipoDto(campeonatoEquipo));
    }

    @PostMapping
    public List<CampeonatoEquipoInputDTO> createAll(@RequestBody List<CampeonatoEquipo> campeonatoEquipos) {
        campeonatoEquipoService.saveAll(campeonatoEquipos);
        return campeonatoEquipos.stream()
                .map(campeonatoEquipoInputMapper::InputCampeonatoEquipoToCampeonatoEquipoDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CampeonatoEquipo> updateCampeonatoEquipo(@PathVariable Long id, @RequestBody CampeonatoEquipo campeonatoEquipoDetails) {
        CampeonatoEquipo campeonatoEquipo = campeonatoEquipoService.findById(id);
        campeonatoEquipo.setCampeonato(campeonatoEquipoDetails.getCampeonato());
        campeonatoEquipo.setEquipo(campeonatoEquipoDetails.getEquipo());

        CampeonatoEquipo updatedCampeonatoEquipo = campeonatoEquipoService.save(campeonatoEquipo);
        return ResponseEntity.ok(updatedCampeonatoEquipo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CampeonatoEquipo> deleteCampeonatoEquipo(@PathVariable Long id) {
        CampeonatoEquipo campeonatoEquipo = campeonatoEquipoService.findById(id);
        campeonatoEquipoService.deleteById(id);
        return ResponseEntity.ok(campeonatoEquipo);
    }
}
