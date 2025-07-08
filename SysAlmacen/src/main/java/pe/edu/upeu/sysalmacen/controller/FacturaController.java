package pe.edu.upeu.sysalmacen.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upeu.sysalmacen.dtos.FacturaDTO;
import pe.edu.upeu.sysalmacen.mappers.FacturaMapper;
import pe.edu.upeu.sysalmacen.model.Factura;
import pe.edu.upeu.sysalmacen.service.IFacturaService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/factura")
@RequiredArgsConstructor
@CrossOrigin("*")

public class FacturaController {
    private final IFacturaService facturaService ;
    private final FacturaMapper facturaMapper;

    @GetMapping
    public ResponseEntity<List<FacturaDTO>> listarTodos() {
        return ResponseEntity.ok(facturaMapper.toDTOs(facturaService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacturaDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(facturaMapper.toDTO(facturaService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<Void> crear(@Valid @RequestBody FacturaDTO dto) {
        Factura creado = facturaService.save(facturaMapper.toEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(creado.getIdFactura()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacturaDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody FacturaDTO dto) {
        dto.setIdFactura(id);
        Factura actualizado = facturaService.update(id, facturaMapper.toEntity(dto));
        return ResponseEntity.ok(facturaMapper.toDTO(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        facturaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
