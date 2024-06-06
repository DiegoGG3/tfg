package app.block5crudvalidation.Jugador.Application.Services;

import app.block5crudvalidation.Jugador.Domain.Entities.Jugador;
import app.block5crudvalidation.Jugador.Domain.Mapper.JugadorInputMapper;
import app.block5crudvalidation.Jugador.Infraestructure.Repository.JugadorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JugadorServiceImpl implements JugadorService {

    private final JugadorRepository jugadorRepository;
    private final JugadorInputMapper jugadorMapper;

    @Override
    public Jugador findById(Long id) {
        return jugadorRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new EntityNotFoundException("Jugador no encontrado con id: " + id));
    }

    @Override
    public Jugador save(Jugador jugador) {
        return jugadorRepository.save(jugador);
    }

    @Override
    public void save2(Jugador jugador) {
        jugadorRepository.save(jugador);
    }


    @Override
    public void deleteById(Long id) {
        if (!jugadorRepository.existsById(Math.toIntExact(id))) {
            throw new EntityNotFoundException("No se puede eliminar, Jugador no encontrado con id: " + id);
        }
        jugadorRepository.deleteById(Math.toIntExact(id));
    }

    @Override
    public List<Jugador> findAll() {
        return jugadorRepository.findAll();
    }

    public void saveAll(List<Jugador> jugadores) {
        jugadorRepository.saveAll(jugadores);
    }
}
