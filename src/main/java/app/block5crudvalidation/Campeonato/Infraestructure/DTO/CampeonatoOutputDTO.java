package app.block5crudvalidation.Campeonato.Infraestructure.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class CampeonatoOutputDTO {
    private Long id;
    private String nombre;
    private Boolean formato;
    private Date fechaInicio;
    private String ganador;
    private String foto;
    private String pais;
}
