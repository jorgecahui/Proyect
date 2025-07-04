package pe.edu.upeu.sysalmacen.dtos;

import lombok.Data;

@Data
public class RepuestoDTO {
    private Long idRepuesto;
    private String nombre;
    private String stockActual;
    private String codigo;
    private String ubicacion;
    private String estado;
    private String stockMinimo;
}
