package pe.edu.upeu.sysalmacen.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacen.dtos.SalidaDTO;
import pe.edu.upeu.sysalmacen.exception.RepuestoNotFoundException;
import pe.edu.upeu.sysalmacen.exception.SalidaNotFoundException;
import pe.edu.upeu.sysalmacen.mappers.SalidaMapper;
import pe.edu.upeu.sysalmacen.model.Repuesto;
import pe.edu.upeu.sysalmacen.model.Salida;
import pe.edu.upeu.sysalmacen.repository.RepuestoRepository;
import pe.edu.upeu.sysalmacen.repository.SalidaRepository;
import pe.edu.upeu.sysalmacen.service.SalidaService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalidaServiceImpl implements SalidaService {

    private final SalidaRepository salidaRepository;
    private final RepuestoRepository repuestoRepository;
    private final SalidaMapper salidaMapper;

    @Override
    public List<SalidaDTO> findAll() {
        return salidaMapper.toDtoList(salidaRepository.findAllWithRepuesto());
    }

    @Override
    public SalidaDTO findById(Long id) {
        return salidaRepository.findById(id)
                .map(salidaMapper::toDto)
                .orElseThrow(() -> new SalidaNotFoundException("Salida no encontrada con ID: " + id));
    }

    @Override
    public SalidaDTO save(SalidaDTO dto) {
        Repuesto repuesto = repuestoRepository.findById(dto.getIdRepuesto())
                .orElseThrow(() -> new RepuestoNotFoundException("Repuesto no encontrado con ID: " + dto.getIdRepuesto()));

        Salida entity = salidaMapper.toEntity(dto);
        entity.setRepuesto(repuesto);

        Salida saved = salidaRepository.save(entity);
        return salidaMapper.toDto(saved);
    }

    @Override
    public SalidaDTO update(Long id, SalidaDTO dto) {
        Salida salida = salidaRepository.findById(id)
                .orElseThrow(() -> new SalidaNotFoundException("Salida no encontrada con ID: " + id));

        Repuesto repuesto = repuestoRepository.findById(dto.getIdRepuesto())
                .orElseThrow(() -> new RepuestoNotFoundException("Repuesto no encontrado con ID: " + dto.getIdRepuesto()));

        salida.setRepuesto(repuesto);
        salida.setCantidadEntregada(dto.getCantidadEntregada());
        salida.setDestinatario(dto.getDestinatario());
        salida.setCodigo(dto.getCodigo());
        salida.setFechaSalida(dto.getFechaSalida());
        salida.setEstado(dto.getEstado());

        Salida updated = salidaRepository.save(salida);
        return salidaMapper.toDto(updated);
    }

    @Override
    public void delete(Long id) {
        if (!salidaRepository.existsById(id)) {
            throw new SalidaNotFoundException("Salida no encontrada con ID: " + id);
        }
        salidaRepository.deleteById(id);
    }
}



