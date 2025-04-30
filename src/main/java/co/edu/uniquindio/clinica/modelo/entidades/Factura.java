package co.edu.uniquindio.clinica.modelo.entidades;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
public class Factura {
    private LocalDateTime fecha;
    private UUID id;
    private Double total,subtotal;
}
