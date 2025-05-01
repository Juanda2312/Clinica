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

    public Servicio buscarServicio(Servicio servicio) {
        ArrayList<Servicio> servicios =  servicioRepositorio.getServiciosDisponibles();
        for (Servicio servicioaux : servicios){
            if (servicio.getId().equals(servicioaux.getId())){
                return servicio;
            }
        }
        return null;
    }


    public ArrayList<Servicio> getServiciosDisponibles() {
        return servicioRepositorio.getServiciosDisponibles();
    }

    public ArrayList<Servicio> getServiciosDisponibles(Suscripcion suscripcion) {
        ArrayList<Servicio> servicios = suscripcion.getServiciosDisponibles();
        servicios.removeIf(servicio -> !getServiciosDisponibles().contains(servicio));
        return suscripcion.getServiciosDisponibles();
    }

    public void registrarServicio(Double precio, String nombre, Suscripcion suscripcion) throws Exception {
        Servicio servicioaux = buscarServicio(nombre);
        if (servicioaux != null) throw new Exception("Ya existe un servicio con esa nombre");
        Servicio servicio = Servicio.builder().id(UUID.randomUUID()).nombre(nombre).precio(precio).build();
        servicioRepositorio.registrarServicio(servicio);
        if (suscripcion != null) {
            suscripcion.AgregarServicio(servicio);
        }
    }

    public void registrarServicio(Double precio, String nombre) throws Exception {
        Servicio servicioaux = buscarServicio(nombre);
        if (servicioaux != null) throw new Exception("Ya existe un servicio con esa nombre");
        Servicio servicio = Servicio.builder().id(UUID.randomUUID()).nombre(nombre).precio(precio).build();
        servicioRepositorio.registrarServicio(servicio);
    }

    public void eliminarServicio(Servicio servicio) throws Exception {
        if (buscarServicio(servicio) == null) throw new Exception("Este servicio no existe");
        servicioRepositorio.eliminarServicio(servicio);
    }
}
