package co.edu.uniquindio.clinica.modelo.entidades;

import co.edu.uniquindio.clinica.modelo.factory.Suscripcion;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
public class Paciente {
    private String telefono,nombre,cedula,email;
    private Suscripcion suscripcion;

    @Override
    public String toString() {
        return  "Nombre= " + nombre +
                ", Cedula= " + cedula +
                ", Telefono= " + telefono +
                ", Correo= " + email +
                ", Tipo de suscripcion= " + suscripcion.getClass().getSimpleName();
    }
}
