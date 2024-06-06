package app.block5crudvalidation.JugadorPartido.Infraestructure.Repository;

import app.block5crudvalidation.JugadorPartido.Domain.Entities.JugadorPartido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorPartidoRepository extends JpaRepository<JugadorPartido, Integer> {
}
