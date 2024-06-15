package app.block5crudvalidation.Partido.Domain.Entities;

import app.block5crudvalidation.Equipo.Domain.Entities.Equipo;
import app.block5crudvalidation.Jornada.Domain.Entities.Jornada;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "partido")
public class Partido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "jornadaId", nullable = false)
    private Jornada jornada;

    @ManyToOne
    @JoinColumn(name = "equipoLocalId", nullable = false)
    private Equipo equipoLocal;

    @ManyToOne
    @JoinColumn(name = "equipoVisitanteId", nullable = false)
    private Equipo equipoVisitante;

    private int golesLocal;
    private int golesVisitante;
    private Date fechaHora;
    private boolean jugado;

    @Override
    public String toString() {
        return "Partido{" +
                "id=" + id +
                ", golesLocal=" + golesLocal +
                ", golesVisitante=" + golesVisitante +
                ", fechaHora=" + fechaHora +
                '}';
    }
}
