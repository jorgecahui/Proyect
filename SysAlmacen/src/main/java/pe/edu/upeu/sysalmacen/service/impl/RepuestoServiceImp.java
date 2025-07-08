package pe.edu.upeu.sysalmacen.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacen.dtos.RepuestoDTO;
import pe.edu.upeu.sysalmacen.mappers.RepuestoMapper;
import pe.edu.upeu.sysalmacen.model.Repuesto;
import pe.edu.upeu.sysalmacen.repository.ICrudGenericoRepository;
import pe.edu.upeu.sysalmacen.repository.IRepuestoRepository;
import pe.edu.upeu.sysalmacen.service.IRepuestoService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RepuestoServiceImp extends CrudGenericoServiceImp<Repuesto, Long> implements IRepuestoService {

    private final IRepuestoRepository repuestoRepository;
    private final RepuestoMapper repuestoMapper;

    @Override
    protected ICrudGenericoRepository<Repuesto, Long> getRepo() {
        return repuestoRepository;
    }

    @Override
    public List<Repuesto> listar() {
        return repuestoRepository.findAll();
    }

    @Override
    public List<RepuestoDTO> listarDto() {
        return repuestoMapper.toDTOs(repuestoRepository.findAll());
    }

    @Override
    public Optional<Repuesto> buscarPorId(Long id) {
        return repuestoRepository.findById(id);
    }

    @Override
    public Repuesto registrar(Repuesto repuesto) {
        // Validaciones adicionales
        if (repuesto.getStockActual() < 0 || repuesto.getStockMinimo() < 0) {
            throw new IllegalArgumentException("Los valores de stock no pueden ser negativos");
        }
        return repuestoRepository.save(repuesto);
    }

    @Override
    public Repuesto actualizar(Repuesto repuesto) {
        // Verificar existencia primero
        if (!repuestoRepository.existsById(repuesto.getIdRepuesto())) {
            throw new RuntimeException("Repuesto no encontrado");
        }
        return repuestoRepository.save(repuesto);
    }

    @Override
    public void eliminar(Long id) {
        repuestoRepository.deleteById(id);
    }
}