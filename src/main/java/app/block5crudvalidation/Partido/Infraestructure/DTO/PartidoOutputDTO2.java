package app.block5crudvalidation.Partido.Infraestructure.DTO;

import app.block5crudvalidation.Equipo.Domain.Entities.Equipo;
import lombok.Data;

import java.util.Date;

@Data
public class PartidoOutputDTO2 {
    private Long id;
    private Long jornadaId;
    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private Integer golesLocal;
    private Integer golesVisitante;
    private Date fechaHora;
}
