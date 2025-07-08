package pe.edu.upeu.sysalmacen.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pe.edu.upeu.sysalmacen.dtos.FacturaDTO;
import pe.edu.upeu.sysalmacen.model.Factura;
import pe.edu.upeu.sysalmacen.model.SolicitudCompra;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FacturaMapper extends GenericMapper<FacturaDTO, Factura> {

    // --- Métodos individuales con mapeo personalizado ---
    @Mappings({
            @Mapping(source = "solicitudCompra.idSolicitudCompra", target = "idSolicitudCompra")
    })
    @Override
    FacturaDTO toDTO(Factura factura);

    @Mappings({
            @Mapping(source = "idSolicitudCompra", target = "solicitudCompra")
    })
    @Override
    Factura toEntity(FacturaDTO facturaDTO);

    // --- Métodos para listas ---
    @Override
    List<FacturaDTO> toDTOs(List<Factura> facturas);

    @Override
    List<Factura> toEntities(List<FacturaDTO> facturaDTOs);

    // --- Métodos auxiliares para convertir entre SolicitudCompra e Integer ---
    default Integer map(SolicitudCompra solicitud) {
        return solicitud != null ? solicitud.getIdSolicitudCompra() : null;
    }

    default SolicitudCompra map(Integer id) {
        if (id == null) return null;
        SolicitudCompra sc = new SolicitudCompra();
        sc.setIdSolicitudCompra(id);
        return sc;
    }
}
