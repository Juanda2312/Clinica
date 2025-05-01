package co.edu.uniquindio.clinica.servicios.Interface;

import co.edu.uniquindio.clinica.modelo.entidades.Cita;
import co.edu.uniquindio.clinica.modelo.entidades.Paciente;
import co.edu.uniquindio.clinica.modelo.entidades.Servicio;
import co.edu.uniquindio.clinica.modelo.factory.Suscripcion;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface IClinicaServicio {

    void registrarPaciente(String telefono, String nombre, String cedula, String email, Suscripcion suscripcion)throws Exception;

    void eliminarPaciente(String cedula)throws Exception;

    ArrayList<Paciente> listarPacientes();

    ArrayList<Cita> listarCitas();

    ArrayList<Servicio> getServiciosDisponibles();

    ArrayList<Servicio> getServiciosDisponibles(Suscripcion suscripcion);

    void registrarServicio(Double precio, String nombre, Suscripcion suscripcion) throws Exception;

    void registrarServicio(Double precio, String nombre) throws Exception;

    void registrarCita(String cedula,  LocalDateTime fecha, Servicio servicio)throws Exception;

    void registrarCitaCorreo(String cedula,  LocalDateTime fecha, Servicio servicio)throws Exception;

    void cancelarCita(Cita cita)throws Exception;



}
