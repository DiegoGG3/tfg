package app.block5crudvalidation.Jugador.Application.Services;


import app.block5crudvalidation.Jugador.Domain.Entities.Jugador;

import java.util.List;

public interface JugadorService {

    Jugador findById(Long id);

    Jugador save(Jugador jugador);

    void save2(Jugador jugador);

    void deleteById(Long id);

    List<Jugador> findAll();

    void saveAll(List<Jugador> jugadores);
}
