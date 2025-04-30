package co.edu.uniquindio.clinica.servicios;

import co.edu.uniquindio.clinica.modelo.entidades.Cita;
import co.edu.uniquindio.clinica.modelo.entidades.Factura;
import co.edu.uniquindio.clinica.modelo.entidades.Paciente;
import co.edu.uniquindio.clinica.modelo.entidades.Servicio;
import co.edu.uniquindio.clinica.repositorios.CitaRepositorio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class CitaServicio {
    private final CitaRepositorio citaRepositorio;

    public CitaServicio() {
        this.citaRepositorio = new CitaRepositorio();
    }

    public void registrarCita(Paciente paciente, LocalDateTime fecha, Servicio servicio) throws Exception {
        String e = "";
        if (fecha.isBefore(LocalDateTime.now())) e = e + "Fecha invalida - ";
        if (servicio == null) e = e + "Servicio invalido - ";
        if (!e.isEmpty()) throw new Exception(e + "Verifique los datos y corrigalos");
        ArrayList<Cita> citas = citaRepositorio.listarCitas();
        for (Cita cita : citas) {
            if (cita.getFecha().isEqual(fecha) && cita.getServicio().equals(servicio) && cita.getFecha().getHour() == fecha.getHour()) {
                throw new Exception("El servicio está ocupado en ese momento");
            }
        }
        Cita cita = Cita.builder().id(UUID.randomUUID()).paciente(paciente).fecha(fecha).servicio(servicio).factura(generarFactura(paciente, servicio)).build();
        citaRepositorio.registrarCita(cita);
    }
    public Factura generarFactura(Paciente paciente, Servicio servicio){
        return paciente.getSuscripcion().generarFacturaCobro(servicio);
    }

    public void cancelarCita(Cita cita)throws Exception{
        ArrayList<Cita> citas = citaRepositorio.listarCitas();
        for (Cita cita1 : citas) {
            if (cita1.equals(cita)) {
                citaRepositorio.eliminarCita(cita);
            }
        }
        throw new Exception("Esta cita no existe");
    }

    public ArrayList<Cita> listarCitas(){
        return citaRepositorio.listarCitas();
    }
}

