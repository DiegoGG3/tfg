package app.block5crudvalidation.CampeonatoEquipo.Domain.Entities;

import java.io.Serializable;
import java.util.Objects;

public class CampeonatoEquipoKey implements Serializable {
    private int campeonato;
    private int equipo;

    public CampeonatoEquipoKey() {}

    public CampeonatoEquipoKey(int campeonato, int equipo) {
        this.campeonato = campeonato;
        this.equipo = equipo;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CampeonatoEquipoKey that = (CampeonatoEquipoKey) o;
        return campeonato == that.campeonato && equipo == that.equipo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(campeonato, equipo);
    }
}
