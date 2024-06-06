package app.block5crudvalidation.Equipo.Infraestructure.DTO;

import lombok.Data;

@Data
public class EquipoInputDTO {
    private Long id;
    private String nombre;
    private Integer anoFundacion;
    private String presidente;
    private Integer numeroPremios;
    private String fotoEscudo;
    private String estadio;
}
