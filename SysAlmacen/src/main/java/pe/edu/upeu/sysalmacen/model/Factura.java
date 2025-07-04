package pe.edu.upeu.sysalmacen.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "Factura")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Factura {
    @Id
    private Integer id_Factura;

    private String fa_archivo_pdf;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal fa_fecha_subida;

    @ManyToOne
    @JoinColumn(name = "id_SolicitudCompra", nullable = false)
    private SolicitudCompra solicitudCompra;
}
