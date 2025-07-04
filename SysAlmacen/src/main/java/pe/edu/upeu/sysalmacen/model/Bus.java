package pe.edu.upeu.sysalmacen.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Bus")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bus {
    @Id
    @Column(name = "id_Bus")
    private Long idBus;

    @Column(nullable = false, length = 20)
    private String bu_placa;

    @Column(length = 50)
    private String bu_modelo;

    @Column(nullable = false, length = 30)
    private String bu_estado;
}
