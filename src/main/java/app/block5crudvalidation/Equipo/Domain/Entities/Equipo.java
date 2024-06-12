package app.block5crudvalidation.Equipo.Domain.Entities;

import app.block5crudvalidation.CampeonatoEquipo.Domain.Entities.CampeonatoEquipo;
import app.block5crudvalidation.Jugador.Domain.Entities.Jugador;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "equipo")
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int equipoId;
    private String nombre;
    private int anoFundacion;
    private String presidente;
    private int numeroPremios;
    private String fotoEscudo;
    private String estadio;

    @OneToMany(mappedBy = "equipo")
    private Set<CampeonatoEquipo> campeonatoEquipos;

    @OneToMany(mappedBy = "equipo")
    @JsonManagedReference
    private List<Jugador> jugadores;

    @Override
    public String toString() {
        return "Equipo{" +
                "nombre='" + nombre + '\'' +
                ", anoFundacion=" + anoFundacion +
                ", presidente='" + presidente + '\'' +
                ", numeroPremios=" + numeroPremios +
                ", fotoEscudo='" + fotoEscudo + '\'' +
                ", estadio='" + estadio + '\'' +
                ", equipoId=" + equipoId +
                '}';
    }
}
