package app.block5crudvalidation.Equipo.Infraestructure.Repository;

import app.block5crudvalidation.Equipo.Domain.Entities.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Integer> {
}
