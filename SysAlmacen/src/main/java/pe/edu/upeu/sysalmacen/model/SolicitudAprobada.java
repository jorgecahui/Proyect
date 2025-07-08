package pe.edu.upeu.sysalmacen.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "solicitud_aprobada")

public class SolicitudAprobada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "fecha_aprobacion", nullable = false)
    private LocalDate fechaAprobacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solicitud", nullable = false)
    private SolicitudRepuesto solicitud;


}
