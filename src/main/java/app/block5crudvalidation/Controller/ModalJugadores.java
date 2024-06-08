package app.block5crudvalidation.Controller;

import app.block5crudvalidation.Campeonato.Domain.Entities.Campeonato;
import app.block5crudvalidation.Campeonato.Infraestructure.Repository.CampeonatoRepository;
import app.block5crudvalidation.Equipo.Domain.Entities.Equipo;
import app.block5crudvalidation.Equipo.Infraestructure.Repository.EquipoRepository;
import app.block5crudvalidation.Jugador.Domain.Entities.Jugador;
import app.block5crudvalidation.Jugador.Infraestructure.Repository.JugadorRepository;
import org.antlr.v4.runtime.misc.EqualityComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ModalJugadores {
    @Autowired
    private CampeonatoRepository campeonatoRepository;

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private JugadorRepository jugadorRepository;

    @GetMapping("/api/jugadores")
    public List<Jugador> getJugadoresByEquipoId(@RequestParam int equipoId) {
        return jugadorRepository.findByEquipoId(equipoId);
    }

    @GetMapping("/api/equipos")
    public List<Equipo> getEquiposByCampeonatoId(@RequestParam int campeonatoId) {
        return equipoRepository.findByCampeonatoId(campeonatoId);
    }
}
