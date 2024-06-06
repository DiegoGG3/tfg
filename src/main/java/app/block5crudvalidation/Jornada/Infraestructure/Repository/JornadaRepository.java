package app.block5crudvalidation.Jornada.Infraestructure.Repository;

import app.block5crudvalidation.Jornada.Domain.Entities.Jornada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JornadaRepository extends JpaRepository<Jornada, Integer> {
}
