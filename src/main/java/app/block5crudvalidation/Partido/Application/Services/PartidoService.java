package app.block5crudvalidation.Partido.Application.Services;


import app.block5crudvalidation.Equipo.Domain.Entities.Equipo;
import app.block5crudvalidation.Jornada.Domain.Entities.Jornada;
import app.block5crudvalidation.Partido.Domain.Entities.Partido;

import java.util.List;

public interface PartidoService {

    Partido findById(Long id);

    Partido save(Partido partido);

    void deleteById(Long id);

    List<Partido> findAll();

    void saveAll(List<Partido> partidos);

    List<Equipo> getAllEquipos();

    List<Jornada> getAllJornadas();

    void addGoal(int partidoId, int jugadorId, String equipoTipo);

    void addAssist(int partidoId, int jugadorId, String equipoTipo);

}