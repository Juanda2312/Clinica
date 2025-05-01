package co.edu.uniquindio.clinica.controladores;

import javafx.event.ActionEvent;
import co.edu.uniquindio.clinica.modelo.entidades.Paciente;
import co.edu.uniquindio.clinica.servicios.ClinicaServicio;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class UsuariosRegistradosController implements Initializable {

    private final ControladorPrincipal controladorPrincipal;
    private final ClinicaServicio clinicaServicio;
    private ObservableList<Paciente> pacientes;
    private Paciente pacienteseleccionado;

    @FXML
    private Button buttonEliminarUsuarios;

    @FXML
    private TableColumn<Paciente, String> columnCedulaUsuario;

    @FXML
    private TableColumn<Paciente, String> columnCorreoUsuario;

    @FXML
    private TableColumn<Paciente, String> columnNombre;

    @FXML
    private TableColumn<Paciente, String> columnTelefonoUsuario;

    @FXML
    private TableColumn<Paciente, String> columnTipoSuscripcion;

    @FXML
    private Pane paneUsuariosRegistrados;

    @FXML
    private TableView<Paciente> tableUsuarios;

    public UsuariosRegistradosController(){
        controladorPrincipal = ControladorPrincipal.getInstancia();
        clinicaServicio = controladorPrincipal.getClinica();
    }



    @FXML
    void EliminarPacienteAction(ActionEvent event) {
        if (pacienteseleccionado != null) {
            try {
                clinicaServicio.eliminarPaciente(pacienteseleccionado.getCedula());
                recargarDatos();
                controladorPrincipal.crearAlerta("Paciente eliminado correctamente", Alert.AlertType.INFORMATION);
            } catch (Exception e) {
                controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
            }
        }else{
            controladorPrincipal.crearAlerta("Seleccione un paciente si desea eliminarlo", Alert.AlertType.ERROR);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        columnCedulaUsuario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCedula()));
        columnTelefonoUsuario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));
        columnCorreoUsuario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        columnTipoSuscripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSuscripcion().getClass().getSimpleName()));
        pacientes = FXCollections.observableArrayList();
        cargarPacientes();
        tableUsuarios.setOnMouseClicked(mouseEvent -> {
            pacienteseleccionado = tableUsuarios.getSelectionModel().getSelectedItem();
        });
        }

    public void cargarPacientes() {
        pacientes.setAll(clinicaServicio.listarPacientes());
        tableUsuarios.setItems(pacientes);
    }

    public void recargarDatos(){
        pacienteseleccionado = null;
        cargarPacientes();
    }
}


