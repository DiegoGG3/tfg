package app.block5crudvalidation.Config.security;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank
    private String gmail;

    @NotBlank
    private String contrasena;
}
