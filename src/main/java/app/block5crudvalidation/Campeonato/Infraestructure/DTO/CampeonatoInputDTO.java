package app.block5crudvalidation.Campeonato.Infraestructure.DTO;

import app.block5crudvalidation.Equipo.Infraestructure.DTO.EquipoOutputDTO;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CampeonatoInputDTO {
    private String nombre;
    private boolean formato;
    private Date fechaInicio;
    private String ganador;
    private String foto;
    private String pais;
    private List<EquipoDTO> equipos;

}

