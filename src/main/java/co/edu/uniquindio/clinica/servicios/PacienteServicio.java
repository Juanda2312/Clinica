package co.edu.uniquindio.clinica.servicios;

import co.edu.uniquindio.clinica.modelo.entidades.Paciente;
import co.edu.uniquindio.clinica.modelo.factory.Suscripcion;
import co.edu.uniquindio.clinica.repositorios.PacienteRepositorio;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class PacienteServicio {
    private final PacienteRepositorio pacienteRepositorio;

    public PacienteServicio() {
        this.pacienteRepositorio = new PacienteRepositorio();
    }

    public void registrarPaciente(String telefono, String nombre, String cedula, String email, Suscripcion suscripcion)throws Exception {
        String e = "";
        if (telefono == null) e = e + "Rellene el telefono - ";
        if (nombre == null) e = e + "Rellene el nombre - ";
        if (cedula == null) e = e + "Rellene la cedula - ";
        if (email == null) e = e + "Rellene el correo - ";
        if (suscripcion == null) e = e + "Seleccione la suscripcion - ";
        if (!e.isEmpty()) throw new Exception(e + "Por favor rellene todos los datos.");
        if (!Pattern.matches("^\\d{10}$\n", telefono)) e = e + "Telefono invalido - ";
        if (!Pattern.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", nombre)) e = e +"Nombre invalido - ";
        if (!Pattern.matches("^\\d{9}$", cedula)) e = e +"Cedula invalida - ";
        if (!Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", email)) e = e +"Email invalido - ";
        if (!e.isEmpty()) throw new Exception(e + "Verifique los datos y corrigalos.");
        Paciente paciente = Paciente.builder().nombre(nombre).cedula(cedula).email(email).telefono(telefono).suscripcion(suscripcion).build();
        pacienteRepositorio.registrarPaciente(paciente);
    }

    public ArrayList<Paciente> listarPacientes(){
        return pacienteRepositorio.listarPacientes();
    }

    public Paciente buscarPaciente(String cedula) throws Exception {
        if (cedula.isEmpty()) throw new Exception("Rellene la cedula del paciente");
        ArrayList<Paciente> pacientes = pacienteRepositorio.listarPacientes();
        for (Paciente paciente : pacientes){
            if (paciente.getCedula().equals(cedula)) return paciente;
        }
        throw new Exception("No se encuentra un paciente registrado con esta cedula");
    }
}
