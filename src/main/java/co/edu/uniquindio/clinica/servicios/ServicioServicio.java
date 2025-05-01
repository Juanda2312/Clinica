package co.edu.uniquindio.clinica.servicios;

import co.edu.uniquindio.clinica.modelo.entidades.Servicio;
import co.edu.uniquindio.clinica.modelo.factory.Suscripcion;
import co.edu.uniquindio.clinica.repositorios.ServicioRepositorio;

import java.util.ArrayList;
import java.util.UUID;

public class ServicioServicio {
    private final ServicioRepositorio servicioRepositorio;

    public ServicioServicio() {
        this.servicioRepositorio = new ServicioRepositorio();
    }

    public Servicio buscarServicio(String nombre) {
        ArrayList<Servicio> servicios =  servicioRepositorio.getServiciosDisponibles();
        for (Servicio servicio : servicios){
            if (servicio.getNombre().equals(nombre)){
                return servicio;
            }
        }
        return null;
    }


    public ArrayList<Servicio> getServiciosDisponibles() {
        return servicioRepositorio.getServiciosDisponibles();
    }

    public ArrayList<Servicio> getServiciosDisponibles(Suscripcion suscripcion) {
        return suscripcion.getServiciosDisponibles();
    }

    public void registrarServicio(Double precio, String nombre, Suscripcion suscripcion) throws Exception {
        Servicio servicioaux = buscarServicio(nombre);
        if (servicioaux != null) throw new Exception("Ya existe un servicio con esa nombre");
        Servicio servicio = Servicio.builder().id(UUID.randomUUID()).nombre(nombre).precio(precio).build();
        servicioRepositorio.registrarServicio(servicio);
        suscripcion.AgregarServicio(servicio);
    }
}
