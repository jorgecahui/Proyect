package pe.edu.upeu.sysalmacen.service.impl;

import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacen.model.Factura;
import pe.edu.upeu.sysalmacen.repository.ICrudGenericoRepository;
import pe.edu.upeu.sysalmacen.service.IFacturaService;

@Service

public class FacturaServiceImp extends CrudGenericoServiceImp<Factura, Integer> implements IFacturaService {
    @Override
    protected ICrudGenericoRepository<Factura, Integer> getRepo() {
        return null;
    }
}
