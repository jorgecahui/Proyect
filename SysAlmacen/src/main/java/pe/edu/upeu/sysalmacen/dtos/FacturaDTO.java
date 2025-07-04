package pe.edu.upeu.sysalmacen.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data

public class FacturaDTO {
    private Integer idFactura;
    private String archivoPdf;
    private BigDecimal fechaSubida;
    private Integer idSolicitudCompra;
}
