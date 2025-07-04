package pe.edu.upeu.sysalmacen.service.impl;

import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacen.model.Repuesto;
import pe.edu.upeu.sysalmacen.repository.ICrudGenericoRepository;
import pe.edu.upeu.sysalmacen.service.IRepuestoService;

@Service

public class RepuestoServiceImp extends CrudGenericoServiceImp<Repuesto, Long> implements IRepuestoService {
    @Override
    protected ICrudGenericoRepository<Repuesto, Long> getRepo() {
        return null;
    }
}
