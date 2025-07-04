package pe.edu.upeu.sysalmacen.service.impl;

import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacen.model.MovimientoInventario;
import pe.edu.upeu.sysalmacen.repository.ICrudGenericoRepository;
import pe.edu.upeu.sysalmacen.service.IFacturaService;
import pe.edu.upeu.sysalmacen.service.IMovimientoInventarioService;

@Service
public class MovimientoInventarioServiceImp extends CrudGenericoServiceImp<MovimientoInventario, Integer> implements IMovimientoInventarioService {
    @Override
    protected ICrudGenericoRepository<MovimientoInventario, Integer> getRepo() {
        return null;
    }
}
