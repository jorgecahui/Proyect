package pe.edu.upeu.sysalmacen.dtos;

import lombok.Data;

import java.time.LocalDate;
@Data
public class SolicitudRepuestoDTO {
    private Long idSolicitudRepuesto;
    private int cantidad;
    private String estado;
    private LocalDate fecha;
    private String motivo;
    private Long idUsuario;
    private Long nombreRepuesto;
    private String placaBus;
    private String proveedor; // opcional, si quieres pedirlo
}

