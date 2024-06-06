package app.block5crudvalidation.CampeonatoEquipo.Application.Services;


import app.block5crudvalidation.CampeonatoEquipo.Domain.Entities.CampeonatoEquipo;

import java.util.List;

public interface CampeonatoEquipoService {
    CampeonatoEquipo findById(Long id);
    CampeonatoEquipo save(CampeonatoEquipo campeonatoEquipo);
    void deleteById(Long id);
    List<CampeonatoEquipo> findAll();
    void saveAll(List<CampeonatoEquipo> campeonatoEquipos);
}
