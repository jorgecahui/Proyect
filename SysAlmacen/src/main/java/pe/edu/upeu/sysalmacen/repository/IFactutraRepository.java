package pe.edu.upeu.sysalmacen.repository;

import org.springframework.stereotype.Repository;
import pe.edu.upeu.sysalmacen.model.Factura;

@Repository
public interface IFactutraRepository extends ICrudGenericoRepository <Factura, Integer>   {
}
