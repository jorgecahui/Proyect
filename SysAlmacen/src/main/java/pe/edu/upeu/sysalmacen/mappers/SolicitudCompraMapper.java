package pe.edu.upeu.sysalmacen.mappers;

import org.mapstruct.Mapper;
import pe.edu.upeu.sysalmacen.dtos.SolicitudCompraDTO;
import pe.edu.upeu.sysalmacen.model.SolicitudCompra;

@Mapper(componentModel = "spring")
public interface SolicitudCompraMapper extends GenericMapper<SolicitudCompraDTO, SolicitudCompra> {

}
