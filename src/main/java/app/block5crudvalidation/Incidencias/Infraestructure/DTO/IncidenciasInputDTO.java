package app.block5crudvalidation.Incidencias.Infraestructure.DTO;

import lombok.Data;

@Data
public class IncidenciasInputDTO {
    private Long id;
    private Long partidoId;
    private Long jugadorId;
    private String tipo;
    private Integer minuto;
    private String descripcion;
}
