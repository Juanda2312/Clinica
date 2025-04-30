package co.edu.uniquindio.clinica.servicios;

import co.edu.uniquindio.clinica.modelo.entidades.Servicio;
import co.edu.uniquindio.clinica.repositorios.ServicioRepositorio;

public class ServicioServicio {
    private final ServicioRepositorio servicioRepositorio;

    public ServicioServicio() {
        this.servicioRepositorio = new ServicioRepositorio();
    }

    public Servicio buscarServicio(String nombre){
        ArrayList<Servicio> servicios =  servicioRepositorio.listarServicios();
        for (Servicio servicio : servicios){
            if (servicio.getNombre().equals(nombre)){
                return servicio;
            }
        }
        throw new Exception("")
    }
}
