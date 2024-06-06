package app.block5crudvalidation.Jornada.Application.Services;

import app.block5crudvalidation.Jornada.Domain.Entities.Jornada;

import java.util.List;

public interface JornadaService {

    Jornada findById(Long id);

    Jornada save(Jornada jornada);

    void deleteById(Long id);

    List<Jornada> findAll();

    void saveAll(List<Jornada> jornadas);
}
