package co.edu.uniquindio.clinica.modelo.entidades;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
public class Cita {
    private Paciente paciente;
    private UUID id;
    private LocalDateTime fecha;
    private Servicio servicio;
    private Factura factura;

    @Override
    public String toString() {
        return "Detalles de cita:\n" +
                "Paciente= " + paciente.toString()+ "\n" +
                "Servicio=" + servicio.toString() + "\n" +
                "Dia=" + fecha.getDayOfMonth() +"/"+ fecha.getMonthValue() +"/"+ fecha.getYear() + "\n"+
                "Hora=" + fecha.getHour() + ":" + fecha.getMinute() + ":" + fecha.getSecond() + "\n" +
                "Factura=" + factura.toString();
    }
}
