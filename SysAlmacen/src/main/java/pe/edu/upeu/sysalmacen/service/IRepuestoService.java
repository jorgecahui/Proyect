package pe.edu.upeu.sysalmacen.service;

import pe.edu.upeu.sysalmacen.dtos.RepuestoDTO;
import pe.edu.upeu.sysalmacen.model.Repuesto;

import java.util.List;
import java.util.Optional;

public interface IRepuestoService extends ICrudGenericoService<Repuesto, Long>{
    List<Repuesto> listar();
    List<RepuestoDTO> listarDto();
    Optional<Repuesto> buscarPorId(Long id);
    Repuesto registrar(Repuesto repuesto);
    Repuesto actualizar(Repuesto repuesto);
    void eliminar(Long id);
}
