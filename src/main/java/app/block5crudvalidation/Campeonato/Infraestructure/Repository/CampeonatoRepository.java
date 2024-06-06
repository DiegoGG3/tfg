package app.block5crudvalidation.Campeonato.Infraestructure.Repository;

import app.block5crudvalidation.Campeonato.Domain.Entities.Campeonato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampeonatoRepository extends JpaRepository<Campeonato, Integer> {
}
