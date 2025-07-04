package pe.edu.upeu.sysalmacen.dtos;

import lombok.Data;

@Data
public class SolicitudCompraDTO {
    private Integer idSolicitudCompra;
    private String cantidad;
    private String estado;
    private Integer idProveedor;
    private Integer idRepuesto;
}
