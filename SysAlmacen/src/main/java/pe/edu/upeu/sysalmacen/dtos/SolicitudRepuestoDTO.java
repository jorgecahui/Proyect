package pe.edu.upeu.sysalmacen.dtos;

import lombok.Data;

import java.time.LocalDate;
@Data

public class SolicitudRepuestoDTO {
    private Long idSolicitudRepuesto;
    private LocalDate fecha;
    private String cantidad;
    private String motivo;
    private String estado;
    private Long idUsuario;
    private String nombreRepuesto;
    private String placaBus;
}
