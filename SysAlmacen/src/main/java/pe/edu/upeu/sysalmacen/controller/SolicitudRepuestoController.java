package pe.edu.upeu.sysalmacen.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upeu.sysalmacen.dtos.SolicitudRepuestoDTO;
import pe.edu.upeu.sysalmacen.mappers.SolicitudRepuestoMapper;
import pe.edu.upeu.sysalmacen.model.SolicitudAprobada;
import pe.edu.upeu.sysalmacen.model.SolicitudRepuesto;
import pe.edu.upeu.sysalmacen.repository.ISolicitudAprobadaRepository;
import pe.edu.upeu.sysalmacen.repository.ISolicitudRepuestoRepository;
import pe.edu.upeu.sysalmacen.service.ISolicitudRepuestoService;


import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/solicitudRepuesto")
@RequiredArgsConstructor
@CrossOrigin("*")

public class SolicitudRepuestoController {
    private final ISolicitudRepuestoService solicitudRepuestoService;
    private final SolicitudRepuestoMapper solicitudRepuestoMapper;
    private final ISolicitudAprobadaRepository solicitudAprobadaRepo;
    private final ISolicitudRepuestoRepository solicitudRepo;

    @GetMapping
    public ResponseEntity<List<SolicitudRepuestoDTO>> findAll() {
        List<SolicitudRepuestoDTO> list = solicitudRepuestoMapper.toDTOs(solicitudRepuestoService.findAll());
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SolicitudRepuestoDTO> findById(@PathVariable("id") Long id) {
        SolicitudRepuesto obj = solicitudRepuestoService.findById(id);
        return ResponseEntity.ok(solicitudRepuestoMapper.toDTO(obj));
    }
    @GetMapping("/pageable")
    public ResponseEntity<Page<SolicitudRepuestoDTO>> listarPageable(Pageable pageable) {
        Page<SolicitudRepuesto> solicitudes = solicitudRepuestoService.listarPageable(pageable);
        Page<SolicitudRepuestoDTO> pageDTO = solicitudes.map(solicitudRepuestoMapper::toDTO);
        return ResponseEntity.ok(pageDTO);
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody SolicitudRepuestoDTO dto) {
        System.out.println("holla_us_dto_12");
        SolicitudRepuesto obj = solicitudRepuestoService.save(solicitudRepuestoMapper.toEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId_SolicitudRepuesto())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    @PostMapping("/solicitudes/{id}/aprobar")
    public ResponseEntity<?> aprobarSolicitud(@PathVariable Long id) {
        SolicitudRepuesto solicitud = solicitudRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Solicitud no encontrada"));

        solicitud.setEstado("Aprobado");
        solicitudRepo.save(solicitud);

        SolicitudAprobada aprobada = new SolicitudAprobada();
        aprobada.setFechaAprobacion(LocalDate.now());
        aprobada.setUsuario(solicitud.getUsuario());
        aprobada.setSolicitud(solicitud);

        solicitudAprobadaRepo.save(aprobada);

        return ResponseEntity.ok("Solicitud aprobada y registrada");
    }
    @PutMapping("/{id}")
    public ResponseEntity<SolicitudRepuestoDTO> update(@Valid @PathVariable("id") Long id,
                                                  @RequestBody SolicitudRepuestoDTO dto) {
        dto.setId_SolicitudRepuesto(id);
        SolicitudRepuesto obj = solicitudRepuestoService.update(id, solicitudRepuestoMapper.toEntity(dto));
        return ResponseEntity.ok(solicitudRepuestoMapper.toDTO(obj));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        solicitudRepuestoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
