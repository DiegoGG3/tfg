package app.block5crudvalidation.CampeonatoEquipo.Application.Services;

import app.block5crudvalidation.CampeonatoEquipo.Domain.Entities.CampeonatoEquipo;
import app.block5crudvalidation.CampeonatoEquipo.Domain.Entities.CampeonatoEquipoKey;
import app.block5crudvalidation.CampeonatoEquipo.Infraestructure.Repository.CampeonatoEquipoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampeonatoEquipoServiceImpl implements CampeonatoEquipoService {
    private final CampeonatoEquipoRepository campeonatoEquipoRepository;

    public CampeonatoEquipo findById(CampeonatoEquipoKey id) {
        return campeonatoEquipoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CampeonatoEquipo no encontrado con id: " + id));
    }

    @Override
    public CampeonatoEquipo findById(Long id) {
        return null;
    }

    @Override
    public CampeonatoEquipo save(CampeonatoEquipo campeonatoEquipo) {
        return campeonatoEquipoRepository.save(campeonatoEquipo);
    }

    @Override
    public void deleteById(Long id) {

    }

    public void deleteById(CampeonatoEquipoKey id) {
        if (!campeonatoEquipoRepository.existsById(id)) {
            throw new EntityNotFoundException("No se puede eliminar, CampeonatoEquipo no encontrado con id: " + id);
        }
        campeonatoEquipoRepository.deleteById(id);
    }

    @Override
    public List<CampeonatoEquipo> findAll() {
        return campeonatoEquipoRepository.findAll();
    }

    @Override
    public void saveAll(List<CampeonatoEquipo> campeonatoEquipos) {
        campeonatoEquipoRepository.saveAll(campeonatoEquipos);
    }

    public List<CampeonatoEquipo> findByCampeonatoId(int campeonatoId) {
        return campeonatoEquipoRepository.findByCampeonatoId(campeonatoId);
    }
}
