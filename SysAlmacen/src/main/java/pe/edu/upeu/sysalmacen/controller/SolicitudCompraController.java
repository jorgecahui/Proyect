package pe.edu.upeu.sysalmacen.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upeu.sysalmacen.dtos.SolicitudCompraDTO;
import pe.edu.upeu.sysalmacen.mappers.SolicitudCompraMapper;
import pe.edu.upeu.sysalmacen.model.SolicitudCompra;
import pe.edu.upeu.sysalmacen.service.ISolicitudCompraService;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/solicitudCompra")
@RequiredArgsConstructor
@CrossOrigin("*")


public class SolicitudCompraController {

    private final ISolicitudCompraService solicitudCompraService ;
    private final SolicitudCompraMapper solicitudCompraMapper;

    @GetMapping
    public ResponseEntity<List<SolicitudCompraDTO>> listarTodos() {
        return ResponseEntity.ok(solicitudCompraMapper.toDTOs(solicitudCompraService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitudCompraDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(solicitudCompraMapper.toDTO(solicitudCompraService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<Void> crear(@Valid @RequestBody SolicitudCompraDTO dto) {
        SolicitudCompra creado = solicitudCompraService.save(solicitudCompraMapper.toEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(creado.getId_SolicitudCompra()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolicitudCompraDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody SolicitudCompraDTO dto) {
        dto.setIdSolicitudCompra(id);
        SolicitudCompra actualizado = solicitudCompraService.update(id, solicitudCompraMapper.toEntity(dto));
        return ResponseEntity.ok(solicitudCompraMapper.toDTO(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        solicitudCompraService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
