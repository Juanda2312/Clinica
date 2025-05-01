package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.entidades.Cita;
import co.edu.uniquindio.clinica.servicios.ClinicaServicio;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class CitasRegistradasController implements Initializable {

    private final ControladorPrincipal controladorPrincipal;
    private final ClinicaServicio clinicaServicio;
    private ObservableList<Cita> citas ;
    private Cita citasSeleccionada;

    @FXML
    private Button buttonCancelar;

    @FXML
    private TableColumn<Cita, String> columnCedulaCitas;

    @FXML
    private TableColumn<Cita, String> columnDiaCitas;

    @FXML
    private TableColumn<Cita, String> columnHoraCitas;

    @FXML
    private TableColumn<Cita, String> columnPrecioCitas;

    @FXML
    private TableColumn<Cita, String> columnServicioCitas;

    @FXML
    private Pane paneCitasRegistradas;

    @FXML
    private TableView<Cita> tableCitas;

    public CitasRegistradasController() {
        controladorPrincipal = ControladorPrincipal.getInstancia();
        clinicaServicio = controladorPrincipal.getClinica();
    }

    @FXML
    void CancelarCitaAction(ActionEvent event) {
        if (citasSeleccionada != null) {
            try{
                clinicaServicio.cancelarCita(citasSeleccionada);
                recargarDatos();
                controladorPrincipal.crearAlerta("Cita cancelada correctamente", Alert.AlertType.INFORMATION);
            }catch (Exception e){
                controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
            }
        }else{
            controladorPrincipal.crearAlerta("Seleccione una cita de la tabla para cancelarla", Alert.AlertType.ERROR);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnCedulaCitas.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPaciente().getCedula()));
        columnServicioCitas.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getServicio().getNombre()));
        columnDiaCitas.setCellValueFactory(cellData -> new SimpleStringProperty( cellData.getValue().getFecha().getDayOfMonth()+"/"+ cellData.getValue().getFecha().getMonthValue() + "/"+ cellData.getValue().getFecha().getYear()));
        columnHoraCitas.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFecha().getHour()+":"+cellData.getValue().getFecha().getMinute()));
        columnPrecioCitas.setCellValueFactory(cellData -> new SimpleStringProperty("Subtotal: " + cellData.getValue().getFactura().getSubtotal() +" Total: " + cellData.getValue().getFactura().getTotal()));
        citas = FXCollections.observableArrayList();
        cargarCitas();
        tableCitas.setOnMouseClicked(event -> {
            citasSeleccionada = tableCitas.getSelectionModel().getSelectedItem();
        });

    }

    public void cargarCitas() {
        citas.setAll(clinicaServicio.listarCitas());
        tableCitas.setItems(citas);
    }

    public void recargarDatos(){
        citasSeleccionada = null;
        cargarCitas();
    }
}
