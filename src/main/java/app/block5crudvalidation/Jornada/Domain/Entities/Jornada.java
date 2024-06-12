package app.block5crudvalidation.Jornada.Domain.Entities;

import app.block5crudvalidation.Campeonato.Domain.Entities.Campeonato;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "jornada")
public class Jornada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "campeonatoId", nullable = false)
    private Campeonato campeonato;

    private int numeroJornada;
    private Date fecha;

    @Override
    public String toString() {
        return "Jornada{" +
                "fecha=" + fecha +
                ", id=" + id +
                ", numeroJornada=" + numeroJornada +
                '}';
    }
}
