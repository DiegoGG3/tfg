package app.block5crudvalidation.Jugador.Domain.Entities;

import app.block5crudvalidation.Equipo.Domain.Entities.Equipo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "jugador")
public class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String apellido1;
    private String apellido2;
    private Date fechaNacimiento;
    private int golesTotales;
    private int asistenciasTotales;
    private String nacionalidad;

    @ManyToOne
    @JoinColumn(name = "equipoId", nullable = false)
    private Equipo equipo;

}
