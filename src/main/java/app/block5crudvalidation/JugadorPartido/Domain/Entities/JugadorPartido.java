package app.block5crudvalidation.JugadorPartido.Domain.Entities;

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
@Table(name = "jugador_partido")
public class JugadorPartido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "partidoId", nullable = false)
    private Partido partido;

    @ManyToOne
    @JoinColumn(name = "jugadorId", nullable = false)
    private Jugador jugador;

    private int minutoEntra;
    private int minutoSale;

}
