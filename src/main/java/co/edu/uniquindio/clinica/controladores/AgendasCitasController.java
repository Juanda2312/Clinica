package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.entidades.Servicio;
import co.edu.uniquindio.clinica.servicios.ClinicaServicio;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AgendasCitasController implements Initializable {

    private final ControladorPrincipal controladorPrincipal;
    private final ClinicaServicio clinicaServicio;

    @FXML
    private ComboBox<String> boxServicios;

    @FXML
    private Button buttonAgendar;

    @FXML
    private Pane paneAgendaCita;

    @FXML
    private DatePicker pickerDia;

    @FXML
    private Spinner<Integer> spinnerHorario;

    @FXML
    private TextField textCedulaCitas;


    public AgendasCitasController() {
        controladorPrincipal = ControladorPrincipal.getInstancia();
        clinicaServicio = controladorPrincipal.getClinica();
    }

    @FXML
    void AgendarCitaAction(ActionEvent event) {
        try{
            clinicaServicio.registrarCita(
                    textCedulaCitas.getText(),
                    pickerDia.getValue().atTime(spinnerHorario.getValue(),0),
                    getServicioSeleccionado()
            );
            limpiarCampos();
            controladorPrincipal.crearAlerta("Cita creada con exito", Alert.AlertType.INFORMATION);

        } catch (Exception e) {
            controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        boxServicios.setItems(FXCollections.observableList(nombrarServicios()));
        boxServicios.getSelectionModel().clearSelection();
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
        spinnerHorario.setValueFactory(valueFactory);
    }

    public void limpiarCampos(){
        textCedulaCitas.clear();
        pickerDia.setValue(null);
        spinnerHorario.getValueFactory().setValue(0);
        boxServicios.getSelectionModel().clearSelection();
    }

    public ArrayList<String> nombrarServicios(){
        ArrayList<String> nombres = new ArrayList<>();
        ArrayList<Servicio> servicios = clinicaServicio.getServiciosDisponibles();
        for (Servicio servicio : servicios) {
            nombres.add(servicio.getNombre());
        }
        return nombres;
    }

    public Servicio getServicioSeleccionado() {
        for (Servicio servicio : clinicaServicio.getServiciosDisponibles()) {
            if (servicio.getNombre().equals(boxServicios.getValue())) {
                return servicio;
            }
        }
        return null;
    }
}
