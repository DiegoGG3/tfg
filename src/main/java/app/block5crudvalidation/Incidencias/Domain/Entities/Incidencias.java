package app.block5crudvalidation.Incidencias.Domain.Entities;

import app.block5crudvalidation.Jugador.Domain.Entities.Jugador;
import app.block5crudvalidation.Partido.Domain.Entities.Partido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    @ToString.Exclude
    private Partido partido;

    @ManyToOne
    @JoinColumn(name = "jugadorId", nullable = false)
    @ToString.Exclude
    private Jugador jugador;

    private int minuto;
    private String tipo;
    private String descripcion;

    // Implementación personalizada de toString()
    @Override
    public String toString() {
        return "Incidencias{" +
                "id=" + id +
                ", partidoId=" + (partido != null ? partido.getId() : "null") + // Mostrar solo el ID del partido
                ", jugadorId=" + (jugador != null ? jugador.getId() : "null") + // Mostrar solo el ID del jugador
                ", minuto=" + minuto +
                ", tipo='" + tipo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
