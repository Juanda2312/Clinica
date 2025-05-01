package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.entidades.Servicio;
import co.edu.uniquindio.clinica.modelo.factory.Suscripcion;
import co.edu.uniquindio.clinica.modelo.factory.SuscripcionBasica;
import co.edu.uniquindio.clinica.modelo.factory.SuscripcionPremium;
import co.edu.uniquindio.clinica.modelo.factory.SuscripcionPremiumPlus;
import co.edu.uniquindio.clinica.servicios.ClinicaServicio;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ServiciosRegistradosController implements Initializable {

    private final ControladorPrincipal controladorPrincipal;
    private final ClinicaServicio clinicaServicio;
    private ObservableList<Servicio> servicios;
    private Servicio servicioseleccionado;

    @FXML
    private Button buttonEliminar;

    @FXML
    private TableColumn<Servicio, String> columnNombre;

    @FXML
    private TableColumn<Servicio, String> columnPrecio;

    @FXML
    private ComboBox<String> boxSuscripciones;

    @FXML
    private Pane paneCitasRegistradas;

    @FXML
    private TableView<Servicio> tableServicios;

    public ServiciosRegistradosController(){
        controladorPrincipal = ControladorPrincipal.getInstancia();
        clinicaServicio = controladorPrincipal.getClinica();
    }
    @FXML
    void eliminarServicioAction(ActionEvent event) {
        if (servicioseleccionado != null) {
            try{
                clinicaServicio.eliminarServicio(servicioseleccionado);
                recargarDatos();
                controladorPrincipal.crearAlerta("Se ha eliminado el servicio correctamente", Alert.AlertType.INFORMATION);
            }catch (Exception e){
                controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
            }
        }else{
            controladorPrincipal.crearAlerta("Seleccione un servicio de la tabla si desea eliminarlo", Alert.AlertType.ERROR);
        }
    }
    @FXML
    public void FiltrarporSuscripcion(ActionEvent event) {
        cargarServicios();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnNombre.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getNombre()));
        columnPrecio.setCellValueFactory(cellData-> new SimpleStringProperty(""+ cellData.getValue().getPrecio()));
        boxSuscripciones.setItems(FXCollections.observableArrayList(new ArrayList<>(Arrays.asList("","Suscripción básica","Suscripción premium","Suscripción Premium Plus"))));
        boxSuscripciones.getSelectionModel().selectFirst();
        servicios = FXCollections.observableArrayList();
        cargarServicios();
        tableServicios.setOnMouseClicked(event -> {
            servicioseleccionado = tableServicios.getSelectionModel().getSelectedItem();
        });

    }

    public Suscripcion determinarSuscripcion() {
        return switch (boxSuscripciones.getValue()) {
            case ("Suscripción básica") -> SuscripcionBasica.getInstancia();
            case ("Suscripción premium") -> SuscripcionPremium.getInstancia();
            case ("Suscripción Premium Plus") -> SuscripcionPremiumPlus.getInstancia();
            default -> null;
        };
    }

    public void cargarServicios(){
        if (determinarSuscripcion() != null) {
            servicios.setAll(clinicaServicio.getServiciosDisponibles(determinarSuscripcion()));
        }else{
            servicios.setAll(clinicaServicio.getServiciosDisponibles());
        }
        tableServicios.setItems(servicios);
    }

    public void recargarDatos(){
        servicioseleccionado = null;
        cargarServicios();
    }
}