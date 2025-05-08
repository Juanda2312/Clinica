package co.edu.uniquindio.clinica.modelo.entidades;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Factura implements Serializable {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fecha;
    private UUID id;
    private Double total,subtotal;

    @Override
    public String toString() {
        return  "Total= " + total +
                ", Subtotal= " + subtotal;
    }
}
