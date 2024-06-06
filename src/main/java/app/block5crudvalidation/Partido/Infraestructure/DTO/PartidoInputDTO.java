package app.block5crudvalidation.Partido.Infraestructure.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class PartidoInputDTO {
    private Long id;
    private Long jornadaId;
    private Long equipoLocalId;
    private Long equipoVisitanteId;
    private Integer golesLocal;
    private Integer golesVisitante;
    private Date fechaHora;
}
