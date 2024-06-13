package app.block5crudvalidation.Incidencias.Application.Services;


import app.block5crudvalidation.Incidencias.Application.Services.IncidenciasService;
import app.block5crudvalidation.Incidencias.Domain.Entities.Incidencias;
import app.block5crudvalidation.Incidencias.Infraestructure.Repository.IncidenciasRepository;
import app.block5crudvalidation.Jugador.Domain.Entities.Jugador;
import app.block5crudvalidation.Jugador.Infraestructure.Repository.JugadorRepository;
import app.block5crudvalidation.Partido.Domain.Entities.Partido;
import app.block5crudvalidation.Partido.Infraestructure.Repository.PartidoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IncidenciasServiceImpl implements IncidenciasService {
    private final IncidenciasRepository incidenciasRepository;
    private final PartidoRepository partidoRepository;
    private final JugadorRepository jugadorRepository;


    @Override
    public Incidencias findById(Long id) {
        return incidenciasRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new EntityNotFoundException("Incidencia no encontrada con id: " + id));
    }

    @Override
    public Incidencias save(Incidencias incidencias) {
        return incidenciasRepository.save(incidencias);
    }

    @Override
    public void deleteById(Long id) {
        if (!incidenciasRepository.existsById(Math.toIntExact(id))) {
            throw new EntityNotFoundException("No se puede eliminar, Incidencia no encontrada con id: " + id);
        }
        incidenciasRepository.deleteById(Math.toIntExact(id));
    }

    @Override
    public List<Incidencias> findAll() {
        return incidenciasRepository.findAll();
    }

    @Override
    public void saveAll(List<Incidencias> incidencias) {
        incidenciasRepository.saveAll(incidencias);
    }

    public void addIncidencia(int partidoId, int jugadorId, int minuto, String tipo, String descripcion) {
        Partido partido = partidoRepository.findById(partidoId)
                .orElseThrow(() -> new EntityNotFoundException("Partido no encontrado con id: " + partidoId));
        Jugador jugador = jugadorRepository.findById(jugadorId)
                .orElseThrow(() -> new EntityNotFoundException("Jugador no encontrado con id: " + jugadorId));

        Incidencias incidencia = new Incidencias();
        incidencia.setPartido(partido);
        incidencia.setJugador(jugador);
        incidencia.setMinuto(minuto);
        incidencia.setTipo(tipo);
        incidencia.setDescripcion(descripcion);

        incidenciasRepository.save(incidencia);
    }

}
