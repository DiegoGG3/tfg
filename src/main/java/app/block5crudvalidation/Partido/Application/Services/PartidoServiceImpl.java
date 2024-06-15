package app.block5crudvalidation.Partido.Application.Services;

import app.block5crudvalidation.CampeonatoEquipo.Domain.Entities.CampeonatoEquipo;
import app.block5crudvalidation.CampeonatoEquipo.Infraestructure.Repository.CampeonatoEquipoRepository;
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
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private CampeonatoEquipoRepository campeonatoEquipoRepository;

    @Transactional
    public void finalizarPartido(Long partidoId) {
        Partido partido = partidoRepository.findById(Math.toIntExact(partidoId)).orElseThrow(() -> new RuntimeException("Partido no encontrado"));
        partido.setJugado(true);
        partidoRepository.save(partido);

        CampeonatoEquipo equipoLocal = campeonatoEquipoRepository.findByCampeonatoAndEquipo(partido.getJornada().getCampeonato(), partido.getEquipoLocal());
        CampeonatoEquipo equipoVisitante = campeonatoEquipoRepository.findByCampeonatoAndEquipo(partido.getJornada().getCampeonato(), partido.getEquipoVisitante());

        equipoLocal.setGolesmarcados(equipoLocal.getGolesmarcados() + partido.getGolesLocal());
        equipoVisitante.setGolesmarcados(equipoVisitante.getGolesmarcados() + partido.getGolesVisitante());

        equipoLocal.setGolesencajados(equipoLocal.getGolesencajados() + partido.getGolesVisitante());
        equipoVisitante.setGolesencajados(equipoVisitante.getGolesencajados() + partido.getGolesLocal());

        equipoLocal.setPj(equipoLocal.getPj() + 1);
        equipoVisitante.setPj(equipoVisitante.getPj() + 1);

        if (partido.getGolesLocal() > partido.getGolesVisitante()) {
            equipoLocal.setPg(equipoLocal.getPg() + 1);
            equipoVisitante.setPp(equipoVisitante.getPp() + 1);
            equipoLocal.setPuntos(equipoLocal.getPuntos() + 3);

        } else if (partido.getGolesLocal() < partido.getGolesVisitante()) {
            equipoVisitante.setPg(equipoVisitante.getPg() + 1);
            equipoLocal.setPp(equipoLocal.getPp() + 1);
            equipoVisitante.setPuntos(equipoVisitante.getPuntos() + 3);

        } else {
            equipoLocal.setPe(equipoLocal.getPe() + 1);
            equipoVisitante.setPe(equipoVisitante.getPe() + 1);
            equipoVisitante.setPuntos(equipoVisitante.getPuntos() + 1);
            equipoLocal.setPuntos(equipoLocal.getPuntos() + 1);
        }

        campeonatoEquipoRepository.save(equipoLocal);
        campeonatoEquipoRepository.save(equipoVisitante);
    }
}
