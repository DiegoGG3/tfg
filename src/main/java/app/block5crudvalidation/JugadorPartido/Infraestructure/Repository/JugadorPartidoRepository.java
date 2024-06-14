package app.block5crudvalidation.JugadorPartido.Infraestructure.Repository;

import app.block5crudvalidation.JugadorPartido.Domain.Entities.JugadorPartido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JugadorPartidoRepository extends JpaRepository<JugadorPartido, Integer> {
    @Query("SELECT jp FROM JugadorPartido jp WHERE jp.partido.id = :partidoId")
    List<JugadorPartido> findByPartidoId(@Param("partidoId") Long partidoId);
}
