package co.edu.uniquindio.clinica.repositorios;

import java.util.ArrayList;
import co.edu.uniquindio.clinica.modelo.entidades.Servicio;
import co.edu.uniquindio.clinica.modelo.factory.Suscripcion;

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
}
