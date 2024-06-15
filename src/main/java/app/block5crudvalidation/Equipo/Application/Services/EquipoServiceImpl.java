package app.block5crudvalidation.Equipo.Application.Services;

import app.block5crudvalidation.Equipo.Domain.Entities.Equipo;
import app.block5crudvalidation.Equipo.Infraestructure.Repository.EquipoRepository;
import app.block5crudvalidation.Jugador.Domain.Entities.Jugador;
import app.block5crudvalidation.Partido.Domain.Entities.Partido;
import app.block5crudvalidation.Partido.Infraestructure.Repository.PartidoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipoServiceImpl implements EquipoService {

    private final EquipoRepository equipoRepository;
    private final PartidoRepository partidoRepository;

    @Override
    public Equipo findById(Long id) {
        return equipoRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new EntityNotFoundException("Equipo no encontrado con id: " + id));
    }

    @Override
    public Equipo save(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    @Override
    public void deleteById(Long id) {
        if (!equipoRepository.existsById(Math.toIntExact(id))) {
            throw new EntityNotFoundException("No se puede eliminar, Equipo no encontrado con id: " + id);
        }
        equipoRepository.deleteById(Math.toIntExact(id));
    }

    @Override
    public List<Equipo> findAll() {
        return equipoRepository.findAll();
    }

    @Override
    public void saveAll(List<Equipo> equipos) {
        equipoRepository.saveAll(equipos);
    }

    @Override
    public List<Jugador> getJugadoresByEquipo(int partidoId, String equipoTipo) {
        Partido partido = partidoRepository.findById(partidoId)
                .orElseThrow(() -> new EntityNotFoundException("Partido no encontrado con id: " + partidoId));

        Equipo equipo;
        if ("local".equals(equipoTipo)) {
            equipo = partido.getEquipoLocal();
        } else if ("visitante".equals(equipoTipo)) {
            equipo = partido.getEquipoVisitante();
        } else {
            throw new IllegalArgumentException("Tipo de equipo no válido: " + equipoTipo);
        }

        return equipoRepository.findJugadoresByEquipoId(equipo.getEquipoId());
    }

    public List<Jugador> findJugadoresByEquipoId(int equipoId) {
        return equipoRepository.findJugadoresByEquipoId(equipoId);
    }


}
