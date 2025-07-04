package pe.edu.upeu.sysalmacen.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacen.dtos.RepuestoDTO;
import pe.edu.upeu.sysalmacen.model.Repuesto;
import pe.edu.upeu.sysalmacen.repository.RepuestoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RepuestoService {

    private final RepuestoRepository repuestoRepository;


    public List<Repuesto> listar() {
        return repuestoRepository.findAll();
    }


    public List<RepuestoDTO> listarDto() {
        return repuestoRepository.findAll().stream()
                .map(r -> new RepuestoDTO(r.getId(), r.getNombre()))
                .collect(Collectors.toList());
    }


    public Optional<Repuesto> buscarPorId(Long id) {
        return repuestoRepository.findById(id);
    }


    public Repuesto registrar(Repuesto repuesto) {
        return repuestoRepository.save(repuesto);
    }


    public Repuesto actualizar(Repuesto repuesto) {
        return repuestoRepository.save(repuesto);
    }


    public void eliminar(Long id) {
        repuestoRepository.deleteById(id);
    }
}
