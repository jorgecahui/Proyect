package pe.edu.upeu.sysalmacen.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pe.edu.upeu.sysalmacen.dtos.SolicitudRepuestoDTO;
import pe.edu.upeu.sysalmacen.model.SolicitudRepuesto;

public interface ISolicitudRepuestoService extends ICrudGenericoService<SolicitudRepuesto, Long> {
    Page<SolicitudRepuesto> listarPageable(Pageable pageable);

    SolicitudRepuesto saveSolicitudConRecepcion(SolicitudRepuestoDTO dto);
}

