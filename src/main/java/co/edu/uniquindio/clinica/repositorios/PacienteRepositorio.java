package co.edu.uniquindio.clinica.repositorios;

import java.util.ArrayList;
import co.edu.uniquindio.clinica.modelo.entidades.Paciente;

public class PacienteRepositorio {
    private final ArrayList<Paciente> pacientes;

    public PacienteRepositorio() {
        pacientes = new ArrayList<>();
    }

    public void registrarPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }

    public void eliminarPaciente(Paciente paciente) throws Exception {
        pacientes.remove(paciente);
    }

    public ArrayList<Paciente> listarPacientes() {
        return pacientes;
    }
}
