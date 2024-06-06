package app.block5crudvalidation.CampeonatoEquipo.Domain.Entities;

import app.block5crudvalidation.Campeonato.Domain.Entities.Campeonato;
import app.block5crudvalidation.Equipo.Domain.Entities.Equipo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "campeonato_equipo")
@IdClass(CampeonatoEquipoKey.class)
public class CampeonatoEquipo {

    @Id
    @ManyToOne
    @JoinColumn(name = "campeonato_id", referencedColumnName = "campeonatoId")
    private Campeonato campeonato;

    @Id
    @ManyToOne
    @JoinColumn(name = "equipo_id", referencedColumnName = "equipoId")
    private Equipo equipo;

    private Integer puntos;

    @Override
    public String toString() {
        return "CampeonatoEquipo{" +
                "puntos=" + puntos +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CampeonatoEquipo that = (CampeonatoEquipo) o;
        return Objects.equals(campeonato, that.campeonato) &&
                Objects.equals(equipo, that.equipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(campeonato, equipo);
    }
}
