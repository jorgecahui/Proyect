package pe.edu.upeu.sysalmacen.dtos;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalidaDTO {
    private Long id;

    @NotNull(message = "El ID del repuesto es obligatorio")
    private Long idRepuesto;

    private String nombreRepuesto;

    private int cantidadEntregada;

    private String destinatario;

    private String codigo;

    private LocalDate fechaSalida;

    private String estado;

}
