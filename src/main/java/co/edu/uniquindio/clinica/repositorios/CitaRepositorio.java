package co.edu.uniquindio.clinica.repositorios;

import co.edu.uniquindio.clinica.modelo.entidades.Cita;
import co.edu.uniquindio.clinica.utils.Constantes;
import co.edu.uniquindio.clinica.utils.Persistencia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CitaRepositorio {
    private final ArrayList<Cita> citas;

    public CitaRepositorio() {
        citas = leerDatos();
    }

    public void registrarCita(Cita cita) {
        citas.add(cita);
        guardarDatos(citas);
    }
    public ArrayList<Cita> listarCitas() {
        return citas;
    }

    public void eliminarCita(Cita cita) {
        citas.remove(cita);
        guardarDatos(citas);
    }

    public void guardarDatos(ArrayList<Cita> citas) {
        try {
            Persistencia.serializarObjeto(Constantes.RUTA_CITAS, citas);
        } catch (IOException e) {
            System.err.println("Error guardando citas: " + e.getMessage());
        }
    }


    public ArrayList<Cita> leerDatos() {
        try {
            List<Cita> datos = Persistencia.deserializarLista(Constantes.RUTA_CITAS,Cita.class);
            if (datos != null) {
                return new ArrayList<>(datos);
            }
        } catch (Exception e) {
            System.err.println("Error cargando citas: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}

