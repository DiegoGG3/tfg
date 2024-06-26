package app.block5crudvalidation.Equipo.Infraestructure.Repository;

import app.block5crudvalidation.Equipo.Domain.Entities.Equipo;
import app.block5crudvalidation.Jugador.Domain.Entities.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Integer> {
    @Query("SELECT e FROM Equipo e JOIN e.campeonatoEquipos ce WHERE ce.campeonato.campeonatoId = :campeonatoId")
    List<Equipo> findByCampeonatoId(@Param("campeonatoId") int campeonatoId);

    @Query("SELECT j FROM Jugador j WHERE j.equipo.equipoId = :equipoId")
    List<Jugador> findJugadoresByEquipoId(@Param("equipoId") int equipoId);
}
