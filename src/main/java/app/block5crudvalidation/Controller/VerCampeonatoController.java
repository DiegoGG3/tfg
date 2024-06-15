package app.block5crudvalidation.Controller;

import app.block5crudvalidation.Campeonato.Application.Services.CampeonatoService;
import app.block5crudvalidation.Campeonato.Domain.Entities.Campeonato;
import app.block5crudvalidation.Campeonato.Infraestructure.DTO.CampeonatoOutputDTO;
import app.block5crudvalidation.CampeonatoEquipo.Application.Services.CampeonatoEquipoService;
import app.block5crudvalidation.CampeonatoEquipo.Domain.Entities.CampeonatoEquipo;
import app.block5crudvalidation.CampeonatoEquipo.Domain.Mapper.CampeonatoEquipoOutputMapper;
import app.block5crudvalidation.CampeonatoEquipo.Infraestructure.DTO.CampeonatoEquipoOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class VerCampeonatoController {

    @Autowired
    private CampeonatoService campeonatoService;

    @Autowired
    private CampeonatoEquipoService campeonatoEquipoService;

    @Autowired
    private CampeonatoEquipoOutputMapper campeonatoEquipoOutputMapper;

    @GetMapping("/VerCampeonato")
    public String getAllCampeonatos(Model model) {
        List<Campeonato> campeonatos = campeonatoService.findAll();
        model.addAttribute("campeonatos", campeonatos);
        return "mostrarcampeonatos";
    }

    @GetMapping("/api/VerCampeonato/{campeonatoId}/clasificacion")
    @ResponseBody
    public List<CampeonatoEquipoOutputDTO> getClasificacion(@PathVariable int campeonatoId) {
        List<CampeonatoEquipo> result = campeonatoEquipoService.findByCampeonatoId(campeonatoId);
        List<CampeonatoEquipoOutputDTO> dtoList = result.stream()
                .map(campeonatoEquipoOutputMapper::OutputCampeonatoEquipoToCampeonatoEquipoDto)
                .collect(Collectors.toList());
        return (dtoList);
    }
}
