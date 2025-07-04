package pe.edu.upeu.sysalmacen.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SolicitudCompra")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudCompra {
    @Id
    private Integer id_SolicitudCompra;

    @Column(nullable = false)
    private String sc_cantidad;

    @Column(nullable = false)
    private String sc_estado;

    @ManyToOne
    @JoinColumn(name = "id_proveedor", nullable = false)
    private Proveedor proveedor;

    @ManyToOne
    @JoinColumn(name = "id_repuesto", nullable = false)
    private Repuesto repuesto;
}