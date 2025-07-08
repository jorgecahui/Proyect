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

    @Column(nullable = false, length = 100, name = "re_nombre")
    private String nombre;

    @Column(nullable = false, name = "re_stock_actual" )
    private int  stockActual;

    @Column(nullable = false, length = 20, name = "re_codigo")
    private String codigo;

    @Column(nullable = false, length = 50, name = "re_ubicacion")
    private String ubicacion;

    @Column(nullable = false, name = "re_estado")
    private String estado; // activo, mantenimiento, inactivo

    @Column(nullable = false, name = "re_stock_minimo")
    private int  stockMinimo;
}