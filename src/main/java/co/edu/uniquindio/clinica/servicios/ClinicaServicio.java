package co.edu.uniquindio.clinica.servicios;

import co.edu.uniquindio.clinica.modelo.entidades.Cita;
import co.edu.uniquindio.clinica.modelo.entidades.Factura;
import co.edu.uniquindio.clinica.modelo.entidades.Paciente;
import co.edu.uniquindio.clinica.modelo.entidades.Servicio;
import co.edu.uniquindio.clinica.modelo.factory.Suscripcion;
import co.edu.uniquindio.clinica.servicios.Interface.IClinicaServicio;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ClinicaServicio implements IClinicaServicio {

    private final CitaServicio citaServicio;
    private final PacienteServicio pacienteServicio;
    private final ServicioServicio servicioServicio;

    public ClinicaServicio() {
        this.pacienteServicio = new PacienteServicio();
        this.servicioServicio = new ServicioServicio();
        this.citaServicio = new CitaServicio();
    }

    public void registrarPaciente(String telefono, String nombre, String cedula, String email, Suscripcion suscripcion)throws Exception {
        pacienteServicio.registrarPaciente(telefono, nombre, cedula, email, suscripcion);
    }

    public ArrayList<Paciente> listarPacientes() {
        return pacienteServicio.listarPacientes();
    }

    public void eliminarPaciente(String cedula)throws Exception{
        pacienteServicio.eliminarPaciente(cedula);
    }

    public void registrarCita(String cedula, LocalDateTime fecha, Servicio servicio)throws Exception{
        Paciente paciente = pacienteServicio.buscarPaciente(cedula);
        citaServicio.registrarCita(paciente, fecha, servicio);
    }

    public void registrarCitaCorreo(String cedula,  LocalDateTime fecha, Servicio servicio)throws Exception{
        Paciente paciente = pacienteServicio.buscarPaciente(cedula);
        citaServicio.registrarCitaCorreo(paciente, fecha, servicio);
    }

    public void cancelarCita(Cita cita)throws Exception{
        citaServicio.cancelarCita(cita);
    }

    public ArrayList<Cita> listarCitas(){
        return citaServicio.listarCitas();
    }

    public Factura generarFactura(String cedula, Servicio servicio) throws Exception {
        Paciente paciente = pacienteServicio.buscarPaciente(cedula);
        return citaServicio.generarFactura(paciente, servicio);
    }

    public ArrayList<Servicio> getServiciosDisponibles(){
        return servicioServicio.getServiciosDisponibles();
    }

    public ArrayList<Servicio> getServiciosDisponibles(Suscripcion suscripcion){
        return servicioServicio.getServiciosDisponibles(suscripcion);
    }

    public void registrarServicio(Double precio, String nombre,Suscripcion suscripcion) throws Exception {
        servicioServicio.registrarServicio(precio, nombre, suscripcion);
    }

    public void registrarServicio(Double precio, String nombre) throws Exception {
        servicioServicio.registrarServicio(precio, nombre);
    }

    public void eliminarServicio(Servicio servicio)throws Exception{
        servicioServicio.eliminarServicio(servicio);
    }

}
