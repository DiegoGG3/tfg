
package app.block5crudvalidation.Equipo.Domain.Entities;

import app.block5crudvalidation.CampeonatoEquipo.Domain.Entities.CampeonatoEquipo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
