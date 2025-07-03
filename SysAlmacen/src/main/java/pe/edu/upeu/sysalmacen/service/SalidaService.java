package pe.edu.upeu.sysalmacen.service;

import pe.edu.upeu.sysalmacen.dtos.SalidaDTO;

import java.util.List;

public interface SalidaService {
    List<SalidaDTO> findAll();
    SalidaDTO findById(Long id);
    SalidaDTO save(SalidaDTO dto);
    SalidaDTO update(Long id, SalidaDTO dto);
    void delete(Long id);
}
