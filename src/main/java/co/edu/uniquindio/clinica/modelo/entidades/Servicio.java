package co.edu.uniquindio.clinica.modelo.entidades;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Getter
@Setter
public class Servicio {
    private Double precio;
    private String nombre;
    private UUID id;

}
