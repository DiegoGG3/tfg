package app.block5crudvalidation.JugadorPartido.Infraestructure.DTO;

import lombok.Data;

@Data
public class JugadorPartidoInputDTO {
    private Long id;
    private Long partidoId;
    private Long jugadorId;
    private Integer minutoEntra;
    private Integer minutoSale;
}
