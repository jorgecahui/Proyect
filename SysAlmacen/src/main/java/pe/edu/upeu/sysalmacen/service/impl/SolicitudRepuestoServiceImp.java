package pe.edu.upeu.sysalmacen.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacen.dtos.SolicitudRepuestoDTO;
import pe.edu.upeu.sysalmacen.mappers.SolicitudRepuestoMapper;
import pe.edu.upeu.sysalmacen.model.Recepcion;
import pe.edu.upeu.sysalmacen.model.Repuesto;
import pe.edu.upeu.sysalmacen.model.SolicitudRepuesto;
import pe.edu.upeu.sysalmacen.model.Usuario;
import pe.edu.upeu.sysalmacen.repository.*;
import pe.edu.upeu.sysalmacen.service.ISolicitudRepuestoService;
import pe.edu.upeu.sysalmacen.model.Bus;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class SolicitudRepuestoServiceImp extends CrudGenericoServiceImp<SolicitudRepuesto, Long> implements ISolicitudRepuestoService {

    private final ISolicitudRepuestoRepository repo;
    private final IUsuarioRepository usuarioRepo;
    private final IBusRepository busRepo;
    private final IRepuestoRepository repuestoRepo;
    private final RecepcionRepository recepcionRepo; // <- asegurado
    private final SolicitudRepuestoMapper repuestoMapper;

    public Page<SolicitudRepuesto> listarPageable(Pageable pageable) {
        return repo.findAll(pageable);
    }


    public SolicitudRepuesto saveSolicitudConRecepcion(SolicitudRepuestoDTO dto) {
        Usuario usuario = usuarioRepo.findById(dto.getIdUsuario())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        Repuesto repuesto = repuestoRepo.findById(dto.getNombreRepuesto())
                .orElseThrow(() -> new EntityNotFoundException("Repuesto no encontrado"));

        Bus bus = busRepo.findById(dto.getPlacaBus())
                .orElseThrow(() -> new EntityNotFoundException("Bus no encontrado"));

        // Crear solicitud
        SolicitudRepuesto solicitud = SolicitudRepuesto.builder()
                .usuario(usuario)
                .repuesto(repuesto)
                .bus(bus)
                .cantidad(dto.getCantidad())
                .estado(dto.getEstado())
                .fecha(dto.getFecha())
                .motivo(dto.getMotivo())
                .build();

        solicitud = repo.save(solicitud);

        // Crear recepción vinculada
        Recepcion recepcion = Recepcion.builder()
                .repuesto(repuesto)
                .cantidadRecibida(dto.getCantidad())
                .codigo("AUTO-" + System.currentTimeMillis()) // Código autogenerado
                .estado("PENDIENTE")
                .proveedor(dto.getProveedor() != null ? dto.getProveedor() : "POR DEFINIR")
                .fechaRecepcion(LocalDate.now())
                .build();

        recepcionRepo.save(recepcion);

        return solicitud;
    }

    @Override
    protected ICrudGenericoRepository<SolicitudRepuesto, Long> getRepo() {
        return repo;
    }
}

