package app.block5crudvalidation.CampeonatoEquipo.Infraestructure.Repository;


import app.block5crudvalidation.Campeonato.Domain.Entities.Campeonato;
import app.block5crudvalidation.CampeonatoEquipo.Domain.Entities.CampeonatoEquipo;
import app.block5crudvalidation.CampeonatoEquipo.Domain.Entities.CampeonatoEquipoKey;
import app.block5crudvalidation.Equipo.Domain.Entities.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampeonatoEquipoRepository extends JpaRepository<CampeonatoEquipo, CampeonatoEquipoKey> {
    @Query("SELECT ce FROM CampeonatoEquipo ce WHERE ce.campeonato.id = :campeonatoId")
    List<CampeonatoEquipo> findByCampeonatoId(@Param("campeonatoId") int campeonatoId);

    @Query("SELECT ce FROM CampeonatoEquipo ce WHERE ce.campeonato = :campeonato AND ce.equipo = :equipo")
    CampeonatoEquipo findByCampeonatoAndEquipo(@Param("campeonato") Campeonato campeonato, @Param("equipo") Equipo equipo);
}
