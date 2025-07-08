package pe.edu.upeu.sysalmacen.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacen.model.Factura;
import pe.edu.upeu.sysalmacen.repository.ICrudGenericoRepository;
import pe.edu.upeu.sysalmacen.repository.IFactutraRepository;
import pe.edu.upeu.sysalmacen.service.IFacturaService;

@Service
@RequiredArgsConstructor

public class FacturaServiceImp extends CrudGenericoServiceImp<Factura, Integer> implements IFacturaService {
    private final IFactutraRepository rep;

    @Override
    protected ICrudGenericoRepository<Factura, Integer> getRepo() {
        return rep;
    }
}
