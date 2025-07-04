package pe.edu.upeu.sysalmacen.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Repuesto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Repuesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_repuesto")
    private Long idRepuesto;

    @Column(nullable = false, length = 100)
    private String re_nombre;

    @Column(nullable = false)
    private String re_stock_actual;

    @Column(nullable = false, length = 20)
    private String re_codigo;

    @Column(nullable = false, length = 50)
    private String re_ubicacion;

    @Column(nullable = false)
    private String re_estado; // activo, mantenimiento, inactivo

    @Column(nullable = false)
    private String re_stock_minimo;
}