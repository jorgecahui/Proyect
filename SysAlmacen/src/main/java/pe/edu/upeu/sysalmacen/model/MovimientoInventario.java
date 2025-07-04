package pe.edu.upeu.sysalmacen.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "MovimientoInventario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoInventario {
    @Id
    private Integer id_MovimientoInventario;

    @Column(nullable = false)
    private String mo_tipo;

    @Column(nullable = false)
    private String mo_cantidad;

    @Column(nullable = false)
    private LocalDate mo_fecha;

    @ManyToOne
    @JoinColumn(name = "id_repuesto", nullable = false)
    private Repuesto repuesto;
}