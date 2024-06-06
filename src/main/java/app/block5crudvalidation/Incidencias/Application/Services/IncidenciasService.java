package app.block5crudvalidation.Incidencias.Application.Services;


import app.block5crudvalidation.Incidencias.Domain.Entities.Incidencias;

import java.util.List;

public interface IncidenciasService {
    Incidencias findById(Long id);
    Incidencias save(Incidencias incidencias);
    void deleteById(Long id);
    List<Incidencias> findAll();
    void saveAll(List<Incidencias> incidencias);
}
