package pe.edu.upeu.sysalmacen.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upeu.sysalmacen.dtos.MovimientoInventarioDTO;
import pe.edu.upeu.sysalmacen.mappers.MovimientoInvetarioMapper;
import pe.edu.upeu.sysalmacen.model.MovimientoInventario;
import pe.edu.upeu.sysalmacen.service.IMovimientoInventarioService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/MovimientoInventario")
@RequiredArgsConstructor
@CrossOrigin("*")

public class MovimientoInventarioController {
    private final IMovimientoInventarioService movimientoInventarioService ;
    private final MovimientoInvetarioMapper movimientoInvetarioMapper;

    @GetMapping
    public ResponseEntity<List<MovimientoInventarioDTO>> listarTodos() {
        return ResponseEntity.ok(movimientoInvetarioMapper.toDTOs(movimientoInventarioService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoInventarioDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(movimientoInvetarioMapper.toDTO(movimientoInventarioService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<Void> crear(@Valid @RequestBody MovimientoInventarioDTO dto) {
        MovimientoInventario creado = movimientoInventarioService.save(movimientoInvetarioMapper.toEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(creado.getId_MovimientoInventario()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimientoInventarioDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody MovimientoInventarioDTO dto) {
        dto.setIdMovimientoInventario(id);
        MovimientoInventario actualizado = movimientoInventarioService.update(id, movimientoInvetarioMapper.toEntity(dto));
        return ResponseEntity.ok(movimientoInvetarioMapper.toDTO(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        movimientoInventarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
