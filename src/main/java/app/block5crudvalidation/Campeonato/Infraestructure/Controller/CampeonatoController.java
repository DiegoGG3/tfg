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
import app.block5crudvalidation.Equipo.Infraestructure.Repository.EquipoRepository;
import app.block5crudvalidation.Jornada.Domain.Entities.Jornada;
import app.block5crudvalidation.Jornada.Infraestructure.Repository.JornadaRepository;
import app.block5crudvalidation.Partido.Domain.Entities.Partido;
import app.block5crudvalidation.Partido.Infraestructure.Repository.PartidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/campeonatos")
public class CampeonatoController {

    private final CampeonatoInputMapper campeonatoInputMapper;
    private final CampeonatoOutputMapper campeonatoOutputMapper;
    private final CampeonatoService campeonatoService;
    private final EquipoService equipoService;
    private final EquipoRepository equipoRepository;
    private final PartidoRepository partidoRepository;
    private final JornadaRepository jornadaRepository;


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
        createJornadas(campeonato);
        createPartidos(campeonato); // Crear los partidos
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
            Equipo equipo = equipoRepository.findById(equipoDTO.getEquipoId())
                    .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

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

    private void createJornadas(Campeonato campeonato) {
        int numEquipos = campeonato.getCampeonatoEquipos().size();
        int numJornadas = campeonato.isFormato() ? (numEquipos - 1) * 2 : numEquipos - 1;
        Set<Jornada> jornadas = new HashSet<>();

        Date fechaInicio = campeonato.getFechaInicio();
        long oneWeekInMillis = 7 * 24 * 60 * 60 * 1000L;

        for (int i = 1; i <= numJornadas; i++) {
            Jornada jornada = new Jornada();
            jornada.setCampeonato(campeonato);
            jornada.setNumeroJornada(i);
            jornada.setFecha(new Date(fechaInicio.getTime() + (i - 1) * oneWeekInMillis));
            jornadas.add(jornada);
        }
        campeonato.setJornadas(jornadas);
        campeonatoService.save(campeonato);  // Actualizar el campeonato con las jornadas
    }

    private void createPartidos(Campeonato campeonato) {
        Set<Jornada> jornadas = campeonato.getJornadas();
        for (Jornada jornada : jornadas) {
            Set<Equipo> equipos = new HashSet<>();
            for (CampeonatoEquipo campeonatoEquipo : campeonato.getCampeonatoEquipos()) {
                equipos.add(campeonatoEquipo.getEquipo());
            }
            List<Equipo> equiposList = new ArrayList<>(equipos);
            // Shuffle the equiposList to ensure randomness in match pairings
            Collections.shuffle(equiposList);

            // If the format is single round-robin, each team plays exactly once
            if (!campeonato.isFormato()) {
                // Pair teams by index
                for (int i = 0; i < equiposList.size() / 2; i++) {
                    Equipo equipoLocal = equiposList.get(i);
                    Equipo equipoVisitante = equiposList.get(i + equiposList.size() / 2);
                    createPartido(jornada, equipoLocal, equipoVisitante);
                }
            } else {
                // If the format is double round-robin, each team plays twice
                for (int i = 0; i < equiposList.size(); i++) {
                    Equipo equipoLocal = equiposList.get(i);
                    for (int j = i + 1; j < equiposList.size(); j++) {
                        Equipo equipoVisitante = equiposList.get(j);
                        createPartido(jornada, equipoLocal, equipoVisitante);
                    }
                }
            }
        }
    }

    private void createPartido(Jornada jornada, Equipo equipoLocal, Equipo equipoVisitante) {
        Partido partido = new Partido();
        partido.setJornada(jornada);
        partido.setEquipoLocal(equipoLocal);
        partido.setEquipoVisitante(equipoVisitante);
        // Puedes establecer la fecha y hora del partido según sea necesario
        partido.setFechaHora(new Date());
        partidoRepository.save(partido);
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
