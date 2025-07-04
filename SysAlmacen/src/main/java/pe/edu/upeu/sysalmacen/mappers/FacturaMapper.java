package pe.edu.upeu.sysalmacen.mappers;

import org.mapstruct.Mapper;
import pe.edu.upeu.sysalmacen.dtos.FacturaDTO;
import pe.edu.upeu.sysalmacen.model.Factura;

@Mapper(componentModel = "spring")
public interface FacturaMapper extends GenericMapper<FacturaDTO,Factura>{

}
