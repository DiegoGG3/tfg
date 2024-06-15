package app.block5crudvalidation.Jugador.Infraestructure.Repository;

import app.block5crudvalidation.Jugador.Domain.Entities.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Integer> {
    @Query("SELECT j FROM Jugador j WHERE j.equipo.equipoId = :equipoId")
    List<Jugador> findByEquipoId(@Param("equipoId") int equipoId);

}
