package pe.edu.upeu.sysalmacen.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.sysalmacen.dtos.SolicitudRepuestoDTO;
import pe.edu.upeu.sysalmacen.model.SolicitudRepuesto;

@Mapper(componentModel = "spring")
public interface SolicitudRepuestoMapper extends GenericMapper<SolicitudRepuestoDTO, SolicitudRepuesto> {
    @Mapping(source = "id_SolicitudRepuesto", target = "idSolicitudRepuesto")
    @Mapping(source = "so_fecha", target = "fecha")
    @Mapping(source = "so_cantidad", target = "cantidad")
    @Mapping(source = "so_motivo", target = "motivo")
    @Mapping(source = "so_estado", target = "estado")
    @Mapping(source = "usuario.idUsuario", target = "idUsuario")
    @Mapping(source = "repuesto.re_nombre", target = "nombreRepuesto")
    @Mapping(source = "bus.bu_placa", target = "placaBus")
    @Override
    SolicitudRepuestoDTO toDTO(SolicitudRepuesto entity);

    @Mapping(target = "id_SolicitudRepuesto", source = "idSolicitudRepuesto")
    @Mapping(target = "so_fecha", source = "fecha")
    @Mapping(target = "so_cantidad", source = "cantidad")
    @Mapping(target = "so_motivo", source = "motivo")
    @Mapping(target = "so_estado", source = "estado")
    @Override
    SolicitudRepuesto toEntity(SolicitudRepuestoDTO dto);
}
