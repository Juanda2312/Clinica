package co.edu.uniquindio.clinica.modelo.entidades;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
public class Cita {
    private Paciente paciente;
    private UUID id;
    private LocalDateTime fecha;
    private Servicio servicio;
    private Factura factura;

}
