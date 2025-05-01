module co.edu.uniquindio.clinica {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.simplejavamail.core;
    requires org.simplejavamail;
    requires static lombok;
    requires java.desktop;

    opens co.edu.uniquindio.clinica to javafx.fxml;
    opens co.edu.uniquindio.clinica.app to javafx.fxml;
    opens co.edu.uniquindio.clinica.controladores to javafx.fxml;

    exports co.edu.uniquindio.clinica.modelo.entidades;
    exports co.edu.uniquindio.clinica.app;
}
