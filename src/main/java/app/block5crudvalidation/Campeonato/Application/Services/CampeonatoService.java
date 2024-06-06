package app.block5crudvalidation.Campeonato.Application.Services;

import app.block5crudvalidation.Campeonato.Domain.Entities.Campeonato;

import java.util.List;

public interface CampeonatoService {

    Campeonato findById(Long id);

    void save(Campeonato campeonato);

    void deleteById(Long id);

    List<Campeonato> findAll();

    void saveAll(List<Campeonato> campeonatos);
}
