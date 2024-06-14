package app.block5crudvalidation.Jugador.Infraestructure.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class JugadorOutputDTOSimple {
    private Long id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private Date fechaNacimiento;

}
