package co.edu.uniquindio.clinica.modelo.factory;

import co.edu.uniquindio.clinica.modelo.entidades.Factura;
import co.edu.uniquindio.clinica.modelo.entidades.Servicio;

import java.util.ArrayList;

public interface Suscripcion {

    ArrayList<Servicio> getServiciosDisponibles();

    Factura generarFacturaCobro(Servicio servicio);

    void AgregarServicio(Servicio servicio);

    void eliminarServicio(Servicio servicio);
}
