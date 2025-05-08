package co.edu.uniquindio.clinica.modelo.factory;

import co.edu.uniquindio.clinica.modelo.entidades.Factura;
import co.edu.uniquindio.clinica.modelo.entidades.Servicio;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class SuscripcionPremium implements Suscripcion, Serializable {

    private static SuscripcionPremium instancia;

    @Getter
    private final ArrayList<Servicio> serviciosDisponibles;

    private SuscripcionPremium() {
        serviciosDisponibles = new ArrayList<>();
    }

    public static SuscripcionPremium getInstancia(){
        if(instancia == null){
            instancia = new SuscripcionPremium();
        }
        return instancia;
    }

    public Factura generarFacturaCobro(Servicio servicio) {
        Factura factura = Factura.builder().id(UUID.randomUUID()).fecha(LocalDateTime.now()).subtotal(servicio.getPrecio()).build();
        if (serviciosDisponibles.contains(servicio)|| SuscripcionBasica.getInstancia().getServiciosDisponibles().contains(servicio)){
            factura.setTotal(0.0);
            return factura;
        }else{
            factura.setTotal(servicio.getPrecio());
            return factura;
            }
        }

    public void AgregarServicio(Servicio servicio) {
        serviciosDisponibles.add(servicio);
    }

    public void eliminarServicio(Servicio servicio) {
        serviciosDisponibles.remove(servicio);
    }
}
