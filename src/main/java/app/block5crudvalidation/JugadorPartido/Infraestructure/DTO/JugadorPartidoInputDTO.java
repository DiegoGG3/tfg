package app.block5crudvalidation.JugadorPartido.Infraestructure.DTO;

import lombok.Data;

@Data
public class JugadorPartidoInputDTO {
    private Long partidoId;
    private Long jugador_id;
    private int minutoEntra;
    private int minutoSale;
}
