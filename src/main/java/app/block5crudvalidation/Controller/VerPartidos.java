package app.block5crudvalidation.Controller;

import app.block5crudvalidation.Partido.Domain.Entities.Partido;
import app.block5crudvalidation.Partido.Infraestructure.Repository.PartidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@RequiredArgsConstructor
public class VerPartidos {
    private final PartidoRepository partidoRepository;

    @GetMapping("/incidenciaspartido/{partidoId}")
    public String getPartidoDetails(@PathVariable("partidoId") Long partidoId, Model model) {
        Partido partido = partidoRepository.findById(Math.toIntExact(partidoId)).orElse(null);
        if (partido != null) {
            model.addAttribute("partido", partido);
        }
        return "partidoDetalles";
    }
}
