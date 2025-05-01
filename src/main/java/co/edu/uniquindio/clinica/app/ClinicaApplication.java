package co.edu.uniquindio.clinica.app;

import co.edu.uniquindio.clinica.controladores.ControladorPrincipal;
import co.edu.uniquindio.clinica.modelo.factory.SuscripcionBasica;
import co.edu.uniquindio.clinica.modelo.factory.SuscripcionPremium;
import co.edu.uniquindio.clinica.servicios.ClinicaServicio;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;

public class ClinicaApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        cargarDatos();
        FXMLLoader fxmlLoader = new FXMLLoader(ClinicaApplication.class.getResource("/co/edu/uniquindio/clinica/PanelPrincipal.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Bienvenidos a la clinica");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void cargarDatos() throws Exception {
        ControladorPrincipal controladorPrincipal = ControladorPrincipal.getInstancia();
        ClinicaServicio clinica = controladorPrincipal.getClinica();
        clinica.registrarPaciente("1234567890","Juanito","1234567890","juandavidtapiero8@gmail.com",SuscripcionBasica.getInstancia());
        clinica.registrarPaciente("0987654321","paco","0123456789","juandavidtapiero22@gmail.com",SuscripcionPremium.getInstancia());
        clinica.registrarServicio(5000.0,"Terapia", SuscripcionPremium.getInstancia());
        clinica.registrarCita("1234567890", LocalDateTime.of(2025,12,12,20,0),clinica.getServiciosDisponibles().getFirst());
        clinica.registrarCita("0123456789", LocalDateTime.of(2025,12,12,12,0),clinica.getServiciosDisponibles().getFirst());
    }
}