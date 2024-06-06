package app.block5crudvalidation.Campeonato.Infraestructure.Controller;


import app.block5crudvalidation.Campeonato.Application.Services.CampeonatoService;
import app.block5crudvalidation.Campeonato.Domain.Entities.Campeonato;
import app.block5crudvalidation.Campeonato.Domain.Mapper.CampeonatoInputMapper;
import app.block5crudvalidation.Campeonato.Domain.Mapper.CampeonatoOutputMapper;
import app.block5crudvalidation.Campeonato.Infraestructure.DTO.CampeonatoInputDTO;
import app.block5crudvalidation.Campeonato.Infraestructure.DTO.CampeonatoOutputDTO;
import app.block5crudvalidation.Campeonato.Infraestructure.DTO.EquipoDTO;
import app.block5crudvalidation.CampeonatoEquipo.Domain.Entities.CampeonatoEquipo;
import app.block5crudvalidation.Equipo.Application.Services.EquipoService;
import app.block5crudvalidation.Equipo.Domain.Entities.Equipo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/campeonatos")
public class CampeonatoController {

    private final CampeonatoInputMapper campeonatoInputMapper;
    private final CampeonatoOutputMapper campeonatoOutputMapper;
    private final CampeonatoService campeonatoService;
    private final EquipoService equipoService;

    @GetMapping
    public ResponseEntity<?> getAllCampeonatos() {
        List<Campeonato> result = campeonatoService.findAll();
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<CampeonatoOutputDTO> dtoList = result.stream()
                    .map(campeonatoOutputMapper::OutputCampeonatoToCampeonatoDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(dtoList);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampeonatoOutputDTO> getCampeonatoById(@PathVariable Long id) {
        Campeonato campeonato = campeonatoService.findById(id);
        return ResponseEntity.ok(campeonatoOutputMapper.OutputCampeonatoToCampeonatoDto(campeonato));
    }

    @PostMapping
    public CampeonatoInputDTO create(@RequestBody CampeonatoInputDTO campeonatoInputDTO) {
        Campeonato campeonato = convertDtoToEntity(campeonatoInputDTO);
        campeonatoService.save(campeonato);
        return convertEntityToDto(campeonato);
    }

    private Campeonato convertDtoToEntity(CampeonatoInputDTO dto) {
        Campeonato campeonato = new Campeonato();
        campeonato.setNombre(dto.getNombre());
        campeonato.setFormato(dto.isFormato());
        campeonato.setFechaInicio(dto.getFechaInicio());
        campeonato.setGanador(dto.getGanador());
        campeonato.setFoto(dto.getFoto());
        campeonato.setPais(dto.getPais());

        Set<CampeonatoEquipo> campeonatoEquipos = new HashSet<>();
        for (EquipoDTO equipoDTO : dto.getEquipos()) {
            Equipo equipo = equipoService.findById((long) equipoDTO.getEquipoId());

            CampeonatoEquipo campeonatoEquipo = new CampeonatoEquipo();
            campeonatoEquipo.setCampeonato(campeonato);
            campeonatoEquipo.setEquipo(equipo);

            campeonatoEquipos.add(campeonatoEquipo);
        }
        campeonato.setCampeonatoEquipos(campeonatoEquipos);

        return campeonato;
    }


    private CampeonatoInputDTO convertEntityToDto(Campeonato campeonato) {
        CampeonatoInputDTO dto = new CampeonatoInputDTO();
        dto.setNombre(campeonato.getNombre());
        dto.setFormato(campeonato.isFormato());
        dto.setFechaInicio(campeonato.getFechaInicio());
        dto.setGanador(campeonato.getGanador());
        dto.setFoto(campeonato.getFoto());
        dto.setPais(campeonato.getPais());

        List<EquipoDTO> equipoDtos = campeonato.getCampeonatoEquipos().stream()
                .map(campeonatoEquipo -> {
                    EquipoDTO equipoDto = new EquipoDTO();
                    equipoDto.setEquipoId(campeonatoEquipo.getEquipo().getEquipoId());
                    return equipoDto;
                })
                .collect(Collectors.toList());

        dto.setEquipos(equipoDtos);
        return dto;
    }



    @PutMapping("/{id}")
    public ResponseEntity<Campeonato> updateCampeonato(@PathVariable Long id, @RequestBody Campeonato campeonatoDetails) {
        Campeonato campeonato = campeonatoService.findById(id);
        campeonato.setNombre(campeonatoDetails.getNombre());
        campeonato.setFormato(campeonatoDetails.isFormato());
        campeonato.setFechaInicio(campeonatoDetails.getFechaInicio());
        campeonato.setGanador(campeonatoDetails.getGanador());
        campeonato.setFoto(campeonatoDetails.getFoto());
        campeonato.setPais(campeonatoDetails.getPais());

        campeonatoService.save(campeonato);
        return ResponseEntity.ok(campeonato);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Campeonato> deleteCampeonato(@PathVariable Long id) {
        Campeonato campeonato = campeonatoService.findById(id);
        campeonatoService.deleteById(id);
        return ResponseEntity.ok(campeonato);
    }
}
