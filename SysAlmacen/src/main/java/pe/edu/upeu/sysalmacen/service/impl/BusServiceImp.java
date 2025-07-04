package pe.edu.upeu.sysalmacen.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacen.model.Bus;
import pe.edu.upeu.sysalmacen.repository.IBusRepository;
import pe.edu.upeu.sysalmacen.repository.ICrudGenericoRepository;
import pe.edu.upeu.sysalmacen.service.IBusService;
@RequiredArgsConstructor
@Service
public class BusServiceImp extends CrudGenericoServiceImp<Bus, String> implements IBusService {
    private final IBusRepository repo;
    @Override
    protected ICrudGenericoRepository<Bus, String> getRepo() {
        return repo;
    }
}
