package pe.edu.upeu.sysalmacen.mappers;

import org.mapstruct.Mapper;
import pe.edu.upeu.sysalmacen.dtos.RepuestoDTO;
import pe.edu.upeu.sysalmacen.model.Repuesto;

@Mapper(componentModel = "spring")
public interface RepuestoMapper extends GenericMapper <RepuestoDTO,Repuesto> {

}
