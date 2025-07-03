package pe.edu.upeu.sysalmacen.mappers;

import org.mapstruct.*;
import pe.edu.upeu.sysalmacen.dtos.RecepcionDTO;
import pe.edu.upeu.sysalmacen.model.Recepcion;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RepuestoMapperHelper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RecepcionMapper {

    @Mappings({
            @Mapping(source = "idRepuesto", target = "repuesto"),
            @Mapping(target = "id", ignore = true)
    })
    Recepcion toEntity(RecepcionDTO dto);

    @Mappings({
            @Mapping(source = "repuesto.id", target = "idRepuesto"),
            @Mapping(source = "repuesto.nombre", target = "nombreRepuesto")
    })
    RecepcionDTO toDto(Recepcion entity);

    @IterableMapping(elementTargetType = RecepcionDTO.class)
    List<RecepcionDTO> toDtoList(List<Recepcion> entities);
}
