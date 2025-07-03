package pe.edu.upeu.sysalmacen.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacen.dtos.SalidaDTO;
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
        return salidaMapper.toDtoList(salidaRepository.findAll());
    }

    @Override
    public SalidaDTO findById(Long id) {
        return salidaRepository.findById(id)
                .map(salidaMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Salida no encontrada"));
    }

    @Override
    public SalidaDTO save(SalidaDTO dto) {
        Repuesto repuesto = repuestoRepository.findById(dto.getIdRepuesto())
                .orElseThrow(() -> new RuntimeException("Repuesto no encontrado"));

        Salida entity = salidaMapper.toEntity(dto);
        entity.setRepuesto(repuesto);

        Salida savedSalida = salidaRepository.save(entity);
        return salidaMapper.toDto(savedSalida);
    }

    @Override
    public SalidaDTO update(Long id, SalidaDTO dto) {
        Salida salida = salidaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Salida no encontrada"));

        Repuesto repuesto = repuestoRepository.findById(dto.getIdRepuesto())
                .orElseThrow(() -> new RuntimeException("Repuesto no encontrado"));

        // Actualizar solo los campos necesarios
        salida.setRepuesto(repuesto);
        salida.setCantidadEntregada(dto.getCantidadEntregada());
        salida.setDestinatario(dto.getDestinatario());
        salida.setCodigo(dto.getCodigo());
        salida.setFechaSalida(dto.getFechaSalida());
        salida.setEstado(dto.getEstado());

        Salida updatedSalida = salidaRepository.save(salida);
        return salidaMapper.toDto(updatedSalida);
    }

    @Override
    public void delete(Long id) {
        salidaRepository.deleteById(id);
    }
}


