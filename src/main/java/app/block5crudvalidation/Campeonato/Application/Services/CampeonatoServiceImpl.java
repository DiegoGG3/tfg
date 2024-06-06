package app.block5crudvalidation.Campeonato.Application.Services;


import app.block5crudvalidation.Campeonato.Domain.Entities.Campeonato;
import app.block5crudvalidation.Campeonato.Infraestructure.Repository.CampeonatoRepository;
import app.block5crudvalidation.CampeonatoEquipo.Domain.Entities.CampeonatoEquipo;
import app.block5crudvalidation.CampeonatoEquipo.Infraestructure.Repository.CampeonatoEquipoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampeonatoServiceImpl implements CampeonatoService {

    private final CampeonatoRepository campeonatoRepository;
    private final CampeonatoEquipoRepository campeonatoEquipoRepository;

    @Override
    public Campeonato findById(Long id) {
        return campeonatoRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new EntityNotFoundException("Campeonato no encontrado con id: " + id));
    }

    @Transactional
    public void save(Campeonato campeonato) {
        campeonatoRepository.save(campeonato);
        campeonato.getCampeonatoEquipos().forEach(campeonatoEquipoRepository::save);
    }
    @Override
    public void deleteById(Long id) {
        if (!campeonatoRepository.existsById(Math.toIntExact(id))) {
            throw new EntityNotFoundException("No se puede eliminar, Campeonato no encontrado con id: " + id);
        }
        campeonatoRepository.deleteById(Math.toIntExact(id));
    }

    @Override
    public List<Campeonato> findAll() {
        return campeonatoRepository.findAll();
    }

    @Override
    public void saveAll(List<Campeonato> campeonatos) {
        for (Campeonato campeonato : campeonatos) {
            campeonatoRepository.save(campeonato);
            for (CampeonatoEquipo campeonatoEquipo : campeonato.getCampeonatoEquipos()) {
                campeonatoEquipoRepository.save(campeonatoEquipo);
            }
        }
    }
}
