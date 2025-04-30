module co.edu.uniquindio.clinica {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.simplejavamail.core;
    requires org.simplejavamail;
    requires static lombok;

    opens co.edu.uniquindio.clinica to javafx.fxml;
    exports co.edu.uniquindio.clinica;
    exports co.edu.uniquindio.clinica.app;
    opens co.edu.uniquindio.clinica.app to javafx.fxml;
}