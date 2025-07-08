package pe.edu.upeu.sysalmacen.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.upeu.sysalmacen.model.Repuesto;

import java.util.Optional;

public interface IRepuestoRepository extends ICrudGenericoRepository <Repuesto, Long>{
    @Query("SELECT r FROM Repuesto r WHERE r.nombre = :nombre")
    Optional<Repuesto> findByNombre(@Param("nombre") String nombre);

}
