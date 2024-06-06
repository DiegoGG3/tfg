package app.block5crudvalidation.Equipo.Application.Services;


import app.block5crudvalidation.Equipo.Domain.Entities.Equipo;
import app.block5crudvalidation.Equipo.Infraestructure.Repository.EquipoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipoServiceImpl implements EquipoService {

    private final EquipoRepository equipoRepository;

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
}
