package app.block5crudvalidation.Incidencias.Domain.Entities;

import app.block5crudvalidation.Jugador.Domain.Entities.Jugador;
import app.block5crudvalidation.Partido.Domain.Entities.Partido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "incidencias")
public class Incidencias {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "partidoId", nullable = false)
    private Partido partido;

    @ManyToOne
    @JoinColumn(name = "jugadorId", nullable = false)
    private Jugador jugador;

    private int minuto;
    private String  tipo;
    private String descripcion;


}
