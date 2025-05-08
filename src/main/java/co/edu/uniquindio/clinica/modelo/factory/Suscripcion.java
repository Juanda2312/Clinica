package co.edu.uniquindio.clinica.modelo.factory;

import co.edu.uniquindio.clinica.modelo.entidades.Factura;
import co.edu.uniquindio.clinica.modelo.entidades.Servicio;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.ArrayList;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public interface Suscripcion {

    ArrayList<Servicio> getServiciosDisponibles();

    Factura generarFacturaCobro(Servicio servicio);

    void AgregarServicio(Servicio servicio);

    void eliminarServicio(Servicio servicio);
}
