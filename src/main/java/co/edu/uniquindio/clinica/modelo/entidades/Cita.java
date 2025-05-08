package co.edu.uniquindio.clinica.modelo.entidades;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cita implements Serializable {
    private Paciente paciente;
    private UUID id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")

    private LocalDateTime fecha;
    private Servicio servicio;
    private Factura factura;

    @Override
    public String toString() {
        return "Detalles de cita:\n" +
                "Paciente:   " + paciente.toString()+ "\n" +
                "Servicio:   " + servicio.toString() + "\n" +
                "Dia:   " + fecha.getDayOfMonth() +"/"+ fecha.getMonthValue() +"/"+ fecha.getYear() + "\n"+
                "Hora:   " + fecha.getHour() + ":" + fecha.getMinute() + ":" + fecha.getSecond() + "\n" +
                "Factura:   " + factura.toString();
    }
}
