package app.block5crudvalidation.Config.security;

import lombok.Data;

@Data
public class UserLoginDTO {
    private String gmail;
    private String contrasena;

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}


