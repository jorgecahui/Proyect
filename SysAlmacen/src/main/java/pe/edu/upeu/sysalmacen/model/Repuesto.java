package pe.edu.upeu.sysalmacen.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "repuestos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Repuesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_repuesto")
    private Long id;

    @Column(name = "nombre", nullable = false, unique = true, length = 100)
    private String nombre;

    @Column(name = "stock_actual", nullable = false)
    private int stockActual;
}

