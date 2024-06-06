package app.block5crudvalidation.Incidencias.Application.Services;


import app.block5crudvalidation.Incidencias.Application.Services.IncidenciasService;
import app.block5crudvalidation.Incidencias.Domain.Entities.Incidencias;
import app.block5crudvalidation.Incidencias.Infraestructure.Repository.IncidenciasRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IncidenciasServiceImpl implements IncidenciasService {
    private final IncidenciasRepository incidenciasRepository;

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
}
