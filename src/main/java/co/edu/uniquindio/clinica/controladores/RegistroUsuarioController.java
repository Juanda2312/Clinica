package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.factory.Suscripcion;
import co.edu.uniquindio.clinica.modelo.factory.SuscripcionBasica;
import co.edu.uniquindio.clinica.modelo.factory.SuscripcionPremium;
import co.edu.uniquindio.clinica.modelo.factory.SuscripcionPremiumPlus;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import co.edu.uniquindio.clinica.servicios.ClinicaServicio;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class RegistroUsuarioController implements Initializable {

    private final ControladorPrincipal controladorPrincipal;
    private final ClinicaServicio clinicaServicio;

    @FXML
    private ComboBox<String> boxSuscripcion;

    @FXML
    private Button buttonCrear;

    @FXML
    private Pane paneRegistroUsuario;

    @FXML
    private TextField textCedulaUsuario;

    @FXML
    private TextField textCorreoUsuario;

    @FXML
    private TextField textNombre;

    @FXML
    private TextField textTelefonoUsuario;

    public RegistroUsuarioController() {
        controladorPrincipal = ControladorPrincipal.getInstancia();
        clinicaServicio = controladorPrincipal.getClinica();
    }

    @FXML
    void CrearUsuarioAction(ActionEvent event) {
        try{
            clinicaServicio.registrarPaciente(
                    textTelefonoUsuario.getText(),
                    textNombre.getText(),
                    textCedulaUsuario.getText(),
                    textCorreoUsuario.getText(),
                    determinarSuscripcion());
            limpiarCampos();
            controladorPrincipal.crearAlerta("El paciente ha sido creado correctamente", Alert.AlertType.INFORMATION);
        }catch (Exception e){
            controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        boxSuscripcion.setItems(FXCollections.observableArrayList(new ArrayList<>(Arrays.asList("","Suscripción básica","Suscripción premium","Suscripción Premium Plus"))));
        boxSuscripcion.getSelectionModel().selectFirst();
    }

    public Suscripcion determinarSuscripcion() {
        return switch (boxSuscripcion.getValue()) {
            case ("Suscripción básica") -> SuscripcionBasica.getInstancia();
            case ("Suscripción premium") -> SuscripcionPremium.getInstancia();
            case ("Suscripción Premium Plus") -> SuscripcionPremiumPlus.getInstancia();
            default -> null;
        };
    }
    public void limpiarCampos(){
        textCedulaUsuario.clear();
        textCorreoUsuario.clear();
        textNombre.clear();
        textTelefonoUsuario.clear();
        boxSuscripcion.getSelectionModel().clearSelection();
    }
}

