package app.block5crudvalidation.CampeonatoEquipo.Infraestructure.DTO;

import app.block5crudvalidation.Equipo.Infraestructure.DTO.EquipoOutputDTO;
import lombok.Data;

@Data
public class CampeonatoEquipoOutputDTO {
    private Long id;
    private Long campeonatoId;
    private EquipoOutputDTO equipo;
    private int pj;
    private int pe;
    private int pp;
    private int pg;
    private int golesmarcados;
    private int golesencajados;

    private Integer puntos;
}
