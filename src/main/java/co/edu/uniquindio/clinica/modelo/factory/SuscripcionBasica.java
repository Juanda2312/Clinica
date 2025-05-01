package co.edu.uniquindio.clinica.modelo.factory;

import co.edu.uniquindio.clinica.modelo.entidades.Factura;
import co.edu.uniquindio.clinica.modelo.entidades.Servicio;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class SuscripcionBasica implements Suscripcion {
    private static SuscripcionBasica instancia;

    @Getter
    private final ArrayList<Servicio> serviciosDisponibles;

    private SuscripcionBasica() {
        serviciosDisponibles = new ArrayList<>();
    }

    public static SuscripcionBasica getInstancia() {
        if (instancia == null) {
            instancia = new SuscripcionBasica();
        }
        return instancia;
    }

    public Factura generarFacturaCobro(Servicio servicio) {
        Factura factura = Factura.builder().id(UUID.randomUUID()).fecha(LocalDateTime.now()).subtotal(servicio.getPrecio()).build();
        for (Servicio servicioDisponible : serviciosDisponibles) {
            if (servicioDisponible.getNombre().equalsIgnoreCase(servicio.getNombre())) {
                factura.setTotal(0.0);
                return factura;
            }
        }
        factura.setTotal(servicio.getPrecio());
        return factura;
    }

    public void AgregarServicio(Servicio servicio) {
        serviciosDisponibles.add(servicio);
    }
}
