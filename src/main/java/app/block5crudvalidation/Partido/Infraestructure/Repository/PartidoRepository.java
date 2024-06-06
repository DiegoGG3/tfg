package app.block5crudvalidation.Partido.Infraestructure.Repository;

import app.block5crudvalidation.Partido.Domain.Entities.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Integer> {
}
