package co.edu.uniquindio.clinica.modelo.entidades;

import co.edu.uniquindio.clinica.modelo.factory.Suscripcion;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Paciente {
    private String telefono,nombre,cedula,email;
    private Suscripcion suscripcion;
}
