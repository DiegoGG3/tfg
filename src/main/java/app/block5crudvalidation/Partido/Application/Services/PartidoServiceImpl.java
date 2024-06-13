package app.block5crudvalidation.Partido.Application.Services;

import app.block5crudvalidation.Equipo.Domain.Entities.Equipo;
import app.block5crudvalidation.Equipo.Infraestructure.Repository.EquipoRepository;
import app.block5crudvalidation.Incidencias.Domain.Entities.Incidencias;
import app.block5crudvalidation.Incidencias.Domain.Mapper.IncidenciasOutputMapper;
import app.block5crudvalidation.Incidencias.Infraestructure.DTO.IncidenciasOutputDTO;
import app.block5crudvalidation.Incidencias.Infraestructure.Repository.IncidenciasRepository;
import app.block5crudvalidation.Jornada.Domain.Entities.Jornada;
import app.block5crudvalidation.Jornada.Infraestructure.Repository.JornadaRepository;
import app.block5crudvalidation.Jugador.Domain.Entities.Jugador;
import app.block5crudvalidation.Jugador.Infraestructure.Repository.JugadorRepository;
import app.block5crudvalidation.Partido.Domain.Entities.Partido;
import app.block5crudvalidation.Partido.Infraestructure.Repository.PartidoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartidoServiceImpl implements PartidoService {

    private final PartidoRepository partidoRepository;
    private final JugadorRepository jugadorRepository;
    private final EquipoRepository equipoRepository;
    private final JornadaRepository jornadaRepository;
    private final IncidenciasRepository incidenciasRepository;
    private final IncidenciasOutputMapper incidenciasOutputMapper;

    @Override
    public Partido findById(Long id) {
        return partidoRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new EntityNotFoundException("Partido no encontrado con id: " + id));
    }

    @Override
    public Partido save(Partido partido) {
        return partidoRepository.save(partido);
    }

    @Override
    public void deleteById(Long id) {
        if (!partidoRepository.existsById(Math.toIntExact(id))) {
            throw new EntityNotFoundException("No se puede eliminar, Partido no encontrado con id: " + id);
        }
        partidoRepository.deleteById(Math.toIntExact(id));
    }

    @Override
    public List<Partido> findAll() {
        return partidoRepository.findAll();
    }

    public void saveAll(List<Partido> partidos) {
        partidoRepository.saveAll(partidos);
    }

    public List<Equipo> getAllEquipos() {
        return equipoRepository.findAll();
    }

    public List<Jornada> getAllJornadas() {
        return jornadaRepository.findAll();
    }

    @Transactional
    public void addGoal(int partidoId, int jugadorId, String equipoTipo) {
        Partido partido = partidoRepository.findById(partidoId)
                .orElseThrow(() -> new EntityNotFoundException("Partido no encontrado con id: " + partidoId));

        Jugador jugador = jugadorRepository.findById(jugadorId)
                .orElseThrow(() -> new EntityNotFoundException("Jugador no encontrado con id: " + jugadorId));

        jugador.setGolesTotales(jugador.getGolesTotales() + 1);
        jugadorRepository.save(jugador);

        if ("local".equals(equipoTipo)) {
            partido.setGolesLocal(partido.getGolesLocal() + 1);
        } else if ("visitante".equals(equipoTipo)) {
            partido.setGolesVisitante(partido.getGolesVisitante() + 1);
        }

        partidoRepository.save(partido);
    }

    @Transactional
    public void addAssist(int partidoId, int jugadorId, String equipoTipo) {
        Partido partido = partidoRepository.findById(partidoId)
                .orElseThrow(() -> new EntityNotFoundException("Partido no encontrado con id: " + partidoId));

        Jugador jugador = jugadorRepository.findById(jugadorId)
                .orElseThrow(() -> new EntityNotFoundException("Jugador no encontrado con id: " + jugadorId));

        jugador.setAsistenciasTotales(jugador.getAsistenciasTotales() + 1);
        jugadorRepository.save(jugador);

        partidoRepository.save(partido);
    }

    public List<Jugador> getJugadoresByPartido(int partidoId) {
        Partido partido = partidoRepository.findById(partidoId)
                .orElseThrow(() -> new EntityNotFoundException("Partido no encontrado con id: " + partidoId));

        List<Jugador> jugadores = new ArrayList<>();

        jugadores.addAll( equipoRepository.findJugadoresByEquipoId(partido.getEquipoVisitante().getEquipoId()));
        jugadores.addAll( equipoRepository.findJugadoresByEquipoId(partido.getEquipoLocal().getEquipoId()));

        return jugadores;
    }


    public ResponseEntity<List<IncidenciasOutputDTO>> getIncidenciasByPartido(int partidoId) {
        List<Incidencias> result = incidenciasRepository.findByPartidoId(partidoId);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<IncidenciasOutputDTO> dtoList = result.stream()
                    .map(incidenciasOutputMapper::OutputIncidenciasToIncidenciasDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(dtoList);
        }
    }
}
