package app.block5crudvalidation.Equipo.Infraestructure.Controller;


import app.block5crudvalidation.Equipo.Application.Services.EquipoService;
import app.block5crudvalidation.Equipo.Domain.Entities.Equipo;
import app.block5crudvalidation.Equipo.Domain.Mapper.EquipoInputMapper;
import app.block5crudvalidation.Equipo.Domain.Mapper.EquipoOutputMapper;
import app.block5crudvalidation.Equipo.Infraestructure.DTO.EquipoInputDTO;
import app.block5crudvalidation.Equipo.Infraestructure.DTO.EquipoOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/equipos")
public class EquipoController {

    private final EquipoInputMapper equipoInputMapper;
    private final EquipoOutputMapper equipoOutputMapper;
    private final EquipoService equipoService;

    @GetMapping
    public ResponseEntity<?> getAllEquipos() {
        List<Equipo> result = equipoService.findAll();
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<EquipoOutputDTO> dtoList = result.stream()
                    .map(equipoOutputMapper::OutputEquipoToEquipoDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(dtoList);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipoOutputDTO> getEquipoById(@PathVariable Long id) {
        Equipo equipo = equipoService.findById(id);
        return ResponseEntity.ok(equipoOutputMapper.OutputEquipoToEquipoDto(equipo));
    }

    @PostMapping("/crear")
    public ResponseEntity<List<EquipoOutputDTO>> createAll(@RequestPart("equipos") List<EquipoInputDTO> equiposInput,
                                                           @RequestPart("file") MultipartFile file) {
        // Validar tipo de archivo
        String contentType = file.getContentType();
        if (!"image/jpeg".equals(contentType) && !"image/png".equals(contentType)) {
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).build();
        }

        // Guardar el archivo en la carpeta especificada
        String folderPath = "src/main/resources/static/imagesCrearEquipo/";
        String fileName = file.getOriginalFilename();
        Path path = Paths.get(folderPath + fileName);
        try {
            Files.copy(file.getInputStream(), path);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        List<Equipo> equipos = equiposInput.stream()
                .map(equipoInputMapper::InputEquipoDtoToEquipo)
                .collect(Collectors.toList());

        // Set the file path to each Equipo entity
        equipos.forEach(equipo -> equipo.setFotoEscudo("/imagesCrearEquipo/" + fileName));

        equipoService.saveAll(equipos);

        List<EquipoOutputDTO> response = equipos.stream()
                .map(equipoOutputMapper::OutputEquipoToEquipoDto)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }



    @PutMapping("/{id}")
    public ResponseEntity<Equipo> updateEquipo(@PathVariable Long id, @RequestBody Equipo equipoDetails) {
        Equipo equipo = equipoService.findById(id);
        equipo.setNombre(equipoDetails.getNombre());
        equipo.setAnoFundacion(equipoDetails.getAnoFundacion());
        equipo.setPresidente(equipoDetails.getPresidente());
        equipo.setNumeroPremios(equipoDetails.getNumeroPremios());
        equipo.setFotoEscudo(equipoDetails.getFotoEscudo());
        equipo.setEstadio(equipoDetails.getEstadio());

        Equipo updatedEquipo = equipoService.save(equipo);
        return ResponseEntity.ok(updatedEquipo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Equipo> deleteEquipo(@PathVariable Long id) {
        Equipo equipo = equipoService.findById(id);
        equipoService.deleteById(id);
        return ResponseEntity.ok(equipo);
    }
}
