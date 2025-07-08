package pe.edu.upeu.sysalmacen.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pe.edu.upeu.sysalmacen.dtos.SolicitudRepuestoDTO;
import pe.edu.upeu.sysalmacen.model.Bus;
import pe.edu.upeu.sysalmacen.model.Repuesto;
import pe.edu.upeu.sysalmacen.model.SolicitudRepuesto;
import pe.edu.upeu.sysalmacen.model.Usuario;

@Mapper(componentModel = "spring")
public interface SolicitudRepuestoMapper extends GenericMapper<SolicitudRepuestoDTO, SolicitudRepuesto> {


    // Métodos auxiliares para entidades
    default Long map(Usuario u) {
        return u != null ? u.getIdUsuario() : null;
    }

    default Long map(Repuesto r) {
        return r != null ? r.getIdRepuesto() : null;
    }

    default Long map(Bus b) {
        return b != null ? b.getIdBus() : null;
    }

    default Usuario mapUsuario(Long id) {
        if (id == null) return null;
        Usuario u = new Usuario();
        u.setIdUsuario(id);
        return u;
    }

    default Repuesto mapRepuesto(Long id) {
        if (id == null) return null;
        Repuesto r = new Repuesto();
        r.setIdRepuesto(id);
        return r;
    }

    default Bus mapBus(Long id) {
        if (id == null) return null;
        Bus b = new Bus();
        b.setIdBus(id);
        return b;
    }
}
