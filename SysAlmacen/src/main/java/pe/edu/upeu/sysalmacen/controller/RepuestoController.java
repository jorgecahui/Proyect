package pe.edu.upeu.sysalmacen.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upeu.sysalmacen.dtos.RepuestoDTO;
import pe.edu.upeu.sysalmacen.mappers.RepuestoMapper;
import pe.edu.upeu.sysalmacen.model.Repuesto;
import pe.edu.upeu.sysalmacen.service.IRepuestoService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/repuesto")
@RequiredArgsConstructor
@CrossOrigin("*")


public class RepuestoController {

    private final IRepuestoService repuestoService ;
    private final RepuestoMapper repuestoMapper;

    @GetMapping
    public ResponseEntity<List<RepuestoDTO>> listarTodos() {
        return ResponseEntity.ok(repuestoMapper.toDTOs(repuestoService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepuestoDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(repuestoMapper.toDTO(repuestoService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<Void> crear(@Valid @RequestBody RepuestoDTO dto) {
        Repuesto creado = repuestoService.save(repuestoMapper.toEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(creado.getIdRepuesto()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RepuestoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody RepuestoDTO dto) {
        dto.setIdRepuesto(id);
        Repuesto actualizado = repuestoService.update(id, repuestoMapper.toEntity(dto));
        return ResponseEntity.ok(repuestoMapper.toDTO(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        repuestoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
