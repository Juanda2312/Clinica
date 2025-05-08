package co.edu.uniquindio.clinica.repositorios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniquindio.clinica.modelo.entidades.Servicio;
import co.edu.uniquindio.clinica.utils.Constantes;
import co.edu.uniquindio.clinica.utils.Persistencia;

public class ServicioRepositorio {

    private final ArrayList<Servicio> servicios;

    public ServicioRepositorio() {
        servicios = leerDatos();
    }

    public ArrayList<Servicio> getServiciosDisponibles(){
        return servicios;
    }

    public void registrarServicio(Servicio servicio){
        servicios.add(servicio);
        guardarDatos(servicios);
    }

    public void eliminarServicio(Servicio servicio) {
        servicios.remove(servicio);
        guardarDatos(servicios);
    }

    public void guardarDatos(ArrayList<Servicio> servicios) {
        try {
            Persistencia.serializarObjeto(Constantes.RUTA_SERVICIOS, servicios);
        } catch (IOException e) {
            System.err.println("Error guardando servicios: " + e.getMessage());
        }
    }


    public ArrayList<Servicio> leerDatos() {
        try {
            List<Servicio> datos = Persistencia.deserializarLista(Constantes.RUTA_SERVICIOS, Servicio.class);
            if (datos != null) {
                return new ArrayList<>(datos);
            }
        } catch (Exception e) {
            System.err.println("Error cargando servicios: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
