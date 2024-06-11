package app.block5crudvalidation.Jornada.Infraestructure.Repository;

import app.block5crudvalidation.Jornada.Domain.Entities.Jornada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JornadaRepository extends JpaRepository<Jornada, Integer> {
    @Query("SELECT j FROM Jornada j WHERE j.campeonato.id = :campeonatoId")
    List<Jornada> findByCampeonatoId(@Param("campeonatoId") Long campeonatoId);
}
