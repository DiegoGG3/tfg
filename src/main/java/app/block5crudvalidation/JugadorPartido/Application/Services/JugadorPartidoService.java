package app.block5crudvalidation.JugadorPartido.Application.Services;


import app.block5crudvalidation.JugadorPartido.Domain.Entities.JugadorPartido;

import java.util.List;

public interface JugadorPartidoService {

    JugadorPartido findById(Long id);

    JugadorPartido save(JugadorPartido jugadorPartido);

    void deleteById(Long id);

    List<JugadorPartido> findAll();

    void saveAll(List<JugadorPartido> jugadorPartidos);
}
