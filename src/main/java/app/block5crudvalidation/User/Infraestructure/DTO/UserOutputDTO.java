package app.block5crudvalidation.User.Infraestructure.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOutputDTO {
    private long id;
    private String nombre;
    private String apellido;
    private String gmail;
    private String contrasena;
    private String rol;
}
