package pe.edu.upeu.sysalmacen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.sysalmacen.model.Repuesto;

import java.util.Optional;

public interface RepuestoRepository extends JpaRepository<Repuesto, Long> {
    Optional<Repuesto> findByNombre(String nombre);
}

