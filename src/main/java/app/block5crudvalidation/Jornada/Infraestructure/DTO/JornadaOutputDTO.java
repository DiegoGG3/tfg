package app.block5crudvalidation.Jornada.Infraestructure.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class JornadaOutputDTO {
    private Long id;
    private Integer numeroJornada;
    private Date fecha;
}
