package app.block5crudvalidation.Partido.Infraestructure.Repository;

import app.block5crudvalidation.Partido.Domain.Entities.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Integer> {
    @Query("SELECT p FROM Partido p WHERE p.jornada.id = :jornadaId")
    List<Partido> findByJornadaId(@Param("jornadaId") Long jornadaId);

}
