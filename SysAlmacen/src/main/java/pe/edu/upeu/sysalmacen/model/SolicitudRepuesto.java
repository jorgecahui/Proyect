package pe.edu.upeu.sysalmacen.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "SolicitudRepuesto")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class SolicitudRepuesto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_SolicitudRepuesto")
    private Long id_SolicitudRepuesto;

    @Column(name = "so_cantidad", nullable = false)
    private String so_cantidad;

    @Column(name = "so_estado", nullable = false)
    private String so_estado;

    @Column(name = "so_fecha", nullable = false)
    private LocalDate so_fecha;

    @Column(nullable = false)
    private String so_motivo;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_repuesto", nullable = false)
    private Repuesto repuesto;

    @ManyToOne
    @JoinColumn(name = "id_Bus", nullable = false)
    private Bus bus;
}
