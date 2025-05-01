package co.edu.uniquindio.clinica.controladores;


import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;


import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;


public class PanelPrincipalController implements Initializable {

    UsuariosRegistradosController usuariosRegistradosController;
    CitasRegistradasController citasRegistradasController;

    @FXML
    private Tab tab1;


    @FXML
    private Tab tab2;


    @FXML
    private Tab tab3;


    @FXML
    private Tab tab4;



    @FXML
    void RecargarPacientes(Event event) {
        usuariosRegistradosController.recargarDatos();
    }

    @FXML
    void RecargarCitas(Event event) {
        citasRegistradasController.recargarDatos();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            cargarTab(tab1, "/co/edu/uniquindio/clinica/RegistroUsuario.fxml");
            usuariosRegistradosController = (UsuariosRegistradosController) cargarTabController(tab2, "/co/edu/uniquindio/clinica/UsuariosRegistrados.fxml");
            cargarTab(tab3, "/co/edu/uniquindio/clinica/AgendasCitas.fxml");
            citasRegistradasController =  (CitasRegistradasController) cargarTabController(tab4, "/co/edu/uniquindio/clinica/CitasRegistradas.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cargarTab(Tab tab, String fxmlFile) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent content = loader.load();
        tab.setContent(content);
    }

    private Initializable cargarTabController(Tab tab, String fxmlFile) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent content = loader.load();
        tab.setContent(content);
        return loader.getController();
    }
}
