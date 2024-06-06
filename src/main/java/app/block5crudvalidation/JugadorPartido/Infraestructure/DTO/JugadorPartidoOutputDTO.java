package app.block5crudvalidation.JugadorPartido.Infraestructure.DTO;

import lombok.Data;

@Data
public class JugadorPartidoOutputDTO {
    private Long id;
    private Long partidoId;
    private Long jugadorId;
    private Integer minutoEntra;
    private Integer minutoSale;
}
