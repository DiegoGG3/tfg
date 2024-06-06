package app.block5crudvalidation.JugadorPartido.Application.Services;


import app.block5crudvalidation.JugadorPartido.Domain.Entities.JugadorPartido;
import app.block5crudvalidation.JugadorPartido.Domain.Mapper.JugadorPartidoInputMapper;
import app.block5crudvalidation.JugadorPartido.Infraestructure.Repository.JugadorPartidoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JugadorPartidoServiceImpl implements JugadorPartidoService {

    private final JugadorPartidoRepository jugadorPartidoRepository;
    private final JugadorPartidoInputMapper jugadorPartidoMapper;

    @Override
    public JugadorPartido findById(Long id) {
        return jugadorPartidoRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new EntityNotFoundException("JugadorPartido no encontrado con id: " + id));
    }

    @Override
    public JugadorPartido save(JugadorPartido jugadorPartido) {
        return jugadorPartidoRepository.save(jugadorPartido);
    }

    @Override
    public void deleteById(Long id) {
        if (!jugadorPartidoRepository.existsById(Math.toIntExact(id))) {
            throw new EntityNotFoundException("No se puede eliminar, JugadorPartido no encontrado con id: " + id);
        }
        jugadorPartidoRepository.deleteById(Math.toIntExact(id));
    }

    @Override
    public List<JugadorPartido> findAll() {
        return jugadorPartidoRepository.findAll();
    }

    public void saveAll(List<JugadorPartido> jugadorPartidos) {
        jugadorPartidoRepository.saveAll(jugadorPartidos);
    }
}
