package app.block5crudvalidation.Incidencias.Infraestructure.Repository;

import app.block5crudvalidation.Incidencias.Domain.Entities.Incidencias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidenciasRepository extends JpaRepository<Incidencias, Integer> {

    @Query("SELECT i FROM Incidencias i WHERE i.partido.id = :partidoId")
    List<Incidencias> findByPartidoId(@Param("partidoId") int partidoId);
}

