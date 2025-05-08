package co.edu.uniquindio.clinica.modelo.entidades;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Servicio implements Serializable {
    private Double precio;
    private String nombre;
    private UUID id;

    @Override
    public String toString() {
        return "Nombre= " + nombre +
                ", Precio= " + precio;
    }
}
