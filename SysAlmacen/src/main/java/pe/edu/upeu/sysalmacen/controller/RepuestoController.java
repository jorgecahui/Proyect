package pe.edu.upeu.sysalmacen.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.sysalmacen.dtos.RepuestoDTO;
import pe.edu.upeu.sysalmacen.model.Repuesto;
import pe.edu.upeu.sysalmacen.repository.RepuestoRepository;
import pe.edu.upeu.sysalmacen.service.RepuestoService;

import java.util.List;

@RestController
@RequestMapping("/api/repuestos")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class RepuestoController {

    private final RepuestoService repuestoService;
    private final RepuestoRepository repuestoRepository;

    // 👉 Listar todos como entidad completa
    @GetMapping("/full")
    public ResponseEntity<List<Repuesto>> listarFull() {
        return ResponseEntity.ok(repuestoService.listar());
    }

    // 👉 Listar como DTO (id y nombre)
    @GetMapping
    public ResponseEntity<List<RepuestoDTO>> findAllDto() {
        List<RepuestoDTO> repuestos = repuestoRepository.findAll().stream()
                .map(r -> new RepuestoDTO(r.getId(), r.getNombre()))
                .toList();
        return ResponseEntity.ok(repuestos);
    }

    // 👉 Buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<Repuesto> obtenerPorId(@PathVariable Long id) {
        return repuestoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 👉 Registrar nuevo
    @PostMapping
    public ResponseEntity<Repuesto> registrar(@RequestBody Repuesto repuesto) {
        return ResponseEntity.ok(repuestoService.registrar(repuesto));
    }

    // 👉 Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<Repuesto> actualizar(@PathVariable Long id, @RequestBody Repuesto repuesto) {
        repuesto.setId(id);
        return ResponseEntity.ok(repuestoService.actualizar(repuesto));
    }

    // 👉 Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        repuestoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

