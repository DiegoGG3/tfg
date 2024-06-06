package app.block5crudvalidation.Jugador.Infraestructure.Repository;

import app.block5crudvalidation.Jugador.Domain.Entities.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Integer> {
}
