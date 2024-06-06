package app.block5crudvalidation.Equipo.Application.Services;

import app.block5crudvalidation.Equipo.Domain.Entities.Equipo;

import java.util.List;

public interface EquipoService {

    Equipo findById(Long id);

    Equipo save(Equipo equipo);

    void deleteById(Long id);

    List<Equipo> findAll();

    void saveAll(List<Equipo> equipos);
}
