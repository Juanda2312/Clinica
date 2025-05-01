package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.factory.Suscripcion;
import co.edu.uniquindio.clinica.modelo.factory.SuscripcionBasica;
import co.edu.uniquindio.clinica.modelo.factory.SuscripcionPremium;
import co.edu.uniquindio.clinica.modelo.factory.SuscripcionPremiumPlus;
import co.edu.uniquindio.clinica.servicios.ClinicaServicio;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
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

public class RegistroServicioController implements Initializable {

    private final ControladorPrincipal controladorPrincipal;
    private final ClinicaServicio clinicaServicio;

    @FXML
    private ComboBox<String> boxSuscripciones;

    @FXML
    private Button buttonRegistrar;

    @FXML
    private Pane paneAgendaCita;

    @FXML
    private TextField textnombre;

    @FXML
    private TextField textprecio;


    public RegistroServicioController(){
        controladorPrincipal = ControladorPrincipal.getInstancia();
        clinicaServicio = controladorPrincipal.getClinica();
    }

    @FXML
    void RegistrarServicioAction(ActionEvent event) {
        if (precio()!=null){
            try {
                clinicaServicio.registrarServicio(
                        precio(),
                        textnombre.getText(),
                        determinarSuscripcion()
                );
                limpiarCampos();
                controladorPrincipal.crearAlerta("Se ha creado el servicio correctamente", Alert.AlertType.INFORMATION);
            }catch (Exception e){
                controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        boxSuscripciones.setItems(FXCollections.observableArrayList(new ArrayList<>(Arrays.asList("","Suscripción básica","Suscripción premium","Suscripción Premium Plus"))));
        boxSuscripciones.getSelectionModel().selectFirst();
    }

    public Suscripcion determinarSuscripcion() {
        return switch (boxSuscripciones.getValue()) {
            case ("Suscripción básica") -> SuscripcionBasica.getInstancia();
            case ("Suscripción premium") -> SuscripcionPremium.getInstancia();
            case ("Suscripción Premium Plus") -> SuscripcionPremiumPlus.getInstancia();
            default -> null;
        };
    }

    public void limpiarCampos(){
        textnombre.clear();
        textprecio.clear();
        boxSuscripciones.getSelectionModel().clearSelection();
    }

    public Double precio(){
        try {
            return Double.valueOf(textprecio.getText());
        }catch (Exception e){
            controladorPrincipal.crearAlerta("Ingrese un precio valido", Alert.AlertType.ERROR);
            return null;
        }
    }
}