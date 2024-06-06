package app.block5crudvalidation.Incidencias.Infraestructure.Repository;

import app.block5crudvalidation.Incidencias.Domain.Entities.Incidencias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidenciasRepository extends JpaRepository<Incidencias, Integer> {
}
