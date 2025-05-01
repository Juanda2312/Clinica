package co.edu.uniquindio.clinica.repositorios;

import java.util.ArrayList;
import co.edu.uniquindio.clinica.modelo.entidades.Servicio;

public class ServicioRepositorio {

    private final ArrayList<Servicio> servicios;

    public ServicioRepositorio() {
        servicios = new ArrayList<>();
    }

    public ArrayList<Servicio> getServiciosDisponibles(){
        return servicios;
    }

    public void registrarServicio(Servicio servicio){
        servicios.add(servicio);
    }

    public void eliminarServicio(Servicio servicio) {servicios.remove(servicio);}
}
