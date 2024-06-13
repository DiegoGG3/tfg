package app.block5crudvalidation.Incidencias.Infraestructure.DTO;

import app.block5crudvalidation.Jugador.Domain.Entities.Jugador;
import lombok.Data;

@Data
public class IncidenciasOutputDTO {
    private Long id;
    private Jugador jugador;
    private String tipo;
    private Integer minuto;
    private String descripcion;
}
