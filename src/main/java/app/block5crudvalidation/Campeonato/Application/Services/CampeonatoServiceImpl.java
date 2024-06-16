package app.block5crudvalidation.Campeonato.Application.Services;


import app.block5crudvalidation.Campeonato.Domain.Entities.Campeonato;
import app.block5crudvalidation.Campeonato.Infraestructure.Repository.CampeonatoRepository;
import app.block5crudvalidation.CampeonatoEquipo.Domain.Entities.CampeonatoEquipo;
import app.block5crudvalidation.CampeonatoEquipo.Infraestructure.Repository.CampeonatoEquipoRepository;
import app.block5crudvalidation.Equipo.Application.Services.EquipoService;
import app.block5crudvalidation.Equipo.Domain.Entities.Equipo;
import app.block5crudvalidation.Equipo.Infraestructure.Repository.EquipoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CampeonatoServiceImpl implements CampeonatoService {

    private final CampeonatoRepository campeonatoRepository;
    private final CampeonatoEquipoRepository campeonatoEquipoRepository;
    private final EquipoRepository equipoRepository;


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

    @Override
    public Campeonato finalizarCampeonato(Long campeonatoId) {
        Campeonato campeonato = campeonatoRepository.findById(Math.toIntExact(campeonatoId))
                .orElseThrow(() -> new RuntimeException("Campeonato no encontrado"));

        Set<CampeonatoEquipo> campeonatoEquipos = campeonato.getCampeonatoEquipos();
        CampeonatoEquipo equipoGanador = campeonatoEquipos.stream()
                .max(Comparator.comparing(CampeonatoEquipo::getPuntos))
                .orElseThrow(() -> new RuntimeException("No hay equipos en el campeonato"));

        Equipo equipo = equipoGanador.getEquipo();
        equipo.setNumeroPremios(equipo.getNumeroPremios() + 1);
        equipoRepository.save(equipo);

        campeonato.setGanador(equipo.getNombre());
        return campeonatoRepository.save(campeonato);
    }
}
