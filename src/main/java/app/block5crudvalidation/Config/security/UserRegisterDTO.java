package app.block5crudvalidation.Config.security;

import lombok.Data;

@Data
public class UserRegisterDTO {
    private String nombre;
    private String apellido;
    private String gmail;
    private String contrasena;
}


