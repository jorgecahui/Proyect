package pe.edu.upeu.sysalmacen.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pe.edu.upeu.sysalmacen.dtos.SolicitudCompraDTO;
import pe.edu.upeu.sysalmacen.model.Proveedor;
import pe.edu.upeu.sysalmacen.model.Repuesto;
import pe.edu.upeu.sysalmacen.model.SolicitudCompra;

@Mapper(componentModel = "spring")
public interface SolicitudCompraMapper extends GenericMapper<SolicitudCompraDTO, SolicitudCompra> {
    @Mappings({
            @Mapping(source = "proveedor.idProveedor", target = "idProveedor"),
            @Mapping(source = "repuesto.idRepuesto", target = "idRepuesto")
    })
    @Override
    SolicitudCompraDTO toDTO(SolicitudCompra entity);

    @Mappings({
            @Mapping(source = "idProveedor", target = "proveedor"),
            @Mapping(source = "idRepuesto", target = "repuesto")
    })
    @Override
    SolicitudCompra toEntity(SolicitudCompraDTO dto);

    default Long map(Proveedor p) {
        return p != null ? p.getIdProveedor() : null;
    }

    default Long map(Repuesto r) {
        return r != null ? r.getIdRepuesto() : null;
    }

    default Proveedor mapProveedor(Long id) {
        if (id == null) return null;
        Proveedor p = new Proveedor();
        p.setIdProveedor(id);
        return p;
    }

    default Repuesto mapRepuesto(Long id) {
        if (id == null) return null;
        Repuesto r = new Repuesto();
        r.setIdRepuesto(id);
        return r;
    }

}
