package app.block5crudvalidation.Jornada.Application.Services;


import app.block5crudvalidation.Jornada.Domain.Entities.Jornada;
import app.block5crudvalidation.Jornada.Infraestructure.Repository.JornadaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JornadaServiceImpl implements JornadaService {

    private final JornadaRepository jornadaRepository;

    @Override
    public Jornada findById(Long id) {
        return jornadaRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new EntityNotFoundException("Jornada no encontrada con id: " + id));
    }

    @Override
    public Jornada save(Jornada jornada) {
        return jornadaRepository.save(jornada);
    }

    @Override
    public void deleteById(Long id) {
        if (!jornadaRepository.existsById(Math.toIntExact(id))) {
            throw new EntityNotFoundException("No se puede eliminar, Jornada no encontrada con id: " + id);
        }
        jornadaRepository.deleteById(Math.toIntExact(id));
    }

    @Override
    public List<Jornada> findAll() {
        return jornadaRepository.findAll();
    }

    @Override
    public void saveAll(List<Jornada> jornadas) {
        jornadaRepository.saveAll(jornadas);
    }
}
