package app.block5crudvalidation.Partido.Application.Services;


import app.block5crudvalidation.Equipo.Domain.Entities.Equipo;
import app.block5crudvalidation.Equipo.Infraestructure.Repository.EquipoRepository;
import app.block5crudvalidation.Jornada.Domain.Entities.Jornada;
import app.block5crudvalidation.Jornada.Infraestructure.Repository.JornadaRepository;
import app.block5crudvalidation.Partido.Domain.Entities.Partido;
import app.block5crudvalidation.Partido.Domain.Mapper.PartidoInputMapper;
import app.block5crudvalidation.Partido.Infraestructure.Repository.PartidoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartidoServiceImpl implements PartidoService {

    private final PartidoRepository partidoRepository;
    private final EquipoRepository equipoRepository;
    private final JornadaRepository jornadaRepository;

    private final PartidoInputMapper partidoMapper;

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
}
