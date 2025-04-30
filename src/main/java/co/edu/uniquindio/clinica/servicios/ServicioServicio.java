package co.edu.uniquindio.clinica.servicios;

import co.edu.uniquindio.clinica.modelo.entidades.Servicio;
import co.edu.uniquindio.clinica.repositorios.ServicioRepositorio;

import java.util.ArrayList;
import java.util.UUID;

public class ServicioServicio {
    private final ServicioRepositorio servicioRepositorio;

    public ServicioServicio() {
        this.servicioRepositorio = new ServicioRepositorio();
    }

    public Servicio buscarServicio(String nombre) throws Exception {
        ArrayList<Servicio> servicios =  servicioRepositorio.getServiciosDisponibles();
        for (Servicio servicio : servicios){
            if (servicio.getNombre().equals(nombre)){
                return servicio;
            }
        }
        throw new Exception("Servicio no encontrado");
    }


    public ArrayList<Servicio> getServiciosDisponibles() {
        servicioRepositorio.getServiciosDisponibles();
    }

    public void registrarServicio(Double precio, String nombre) throws Exception {
        ArrayList<Servicio>servicios =  servicioRepositorio.getServiciosDisponibles();
        for (Servicio servicio : servicios){
            if (servicio.getNombre().equalsIgnoreCase(nombre)){
                throw  new Exception("Ya existe este servicio");
            }
        }
        Servicio servicio = Servicio.builder().id(UUID.randomUUID()).nombre(nombre).precio(precio).build();
        servicioRepositorio.registrarServicio(servicio);
    }
}
