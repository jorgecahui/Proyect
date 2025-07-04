package pe.edu.upeu.sysalmacen.mappers;

import org.mapstruct.Mapper;
import pe.edu.upeu.sysalmacen.dtos.MovimientoInventarioDTO;
import pe.edu.upeu.sysalmacen.model.MovimientoInventario;

@Mapper(componentModel = "spring")
public interface MovimientoInvetarioMapper extends GenericMapper <MovimientoInventarioDTO, MovimientoInventario> {
}
