package co.edu.uniquindio.clinica.modelo.entidades;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Builder
@Getter
@Setter
public class Servicio {
    private Double precio;
    private String nombre;
    private UUID id;

    @Override
    public String toString() {
        return "Nombre= " + nombre +
                ", Precio= " + precio;
    }
}
