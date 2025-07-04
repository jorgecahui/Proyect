package pe.edu.upeu.sysalmacen.service.impl;

import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacen.model.SolicitudCompra;
import pe.edu.upeu.sysalmacen.repository.ICrudGenericoRepository;
import pe.edu.upeu.sysalmacen.service.ISolicitudCompraService;

@Service

public class SolicitudCompraImp extends CrudGenericoServiceImp<SolicitudCompra, Integer> implements ISolicitudCompraService {
    @Override
    protected ICrudGenericoRepository<SolicitudCompra, Integer> getRepo() {
        return null;
    }
}
