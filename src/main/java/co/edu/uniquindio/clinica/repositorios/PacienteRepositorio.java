package co.edu.uniquindio.clinica.repositorios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniquindio.clinica.modelo.entidades.Paciente;
import co.edu.uniquindio.clinica.utils.Constantes;
import co.edu.uniquindio.clinica.utils.Persistencia;

public class PacienteRepositorio {
    private final ArrayList<Paciente> pacientes;

    public PacienteRepositorio() {
        this.pacientes = leerDatos();
    }

    public void registrarPaciente(Paciente paciente) {
        pacientes.add(paciente);
        guardarDatos(pacientes);
    }

    public void eliminarPaciente(Paciente paciente){
        pacientes.remove(paciente);
        guardarDatos(pacientes);
    }

    public ArrayList<Paciente> listarPacientes() {
        return pacientes;
    }

    public void guardarDatos(ArrayList<Paciente> pacientes) {
        try {
            Persistencia.serializarObjeto(Constantes.RUTA_PACIENTES, pacientes);
        } catch (IOException e) {
            System.err.println("Error guardando pacientes: " + e.getMessage());
        }
    }


    public ArrayList<Paciente> leerDatos() {
        try {
            List<Paciente> datos = Persistencia.deserializarLista(Constantes.RUTA_PACIENTES, Paciente.class);
            if (datos != null) {
                return new ArrayList<>(datos);
            }
        } catch (Exception e) {
            System.err.println("Error cargando pacientes: " + e.getMessage());
        }
        return new ArrayList<>();
    }

}
