module co.edu.uniquindio.clinica {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.simplejavamail.core;
    requires org.simplejavamail;
    requires static lombok;
    requires com.fasterxml.jackson.dataformat.xml;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires com.fasterxml.jackson.databind;

    opens co.edu.uniquindio.clinica to javafx.fxml;
    opens co.edu.uniquindio.clinica.app to javafx.fxml;
    opens co.edu.uniquindio.clinica.controladores to javafx.fxml;
    opens co.edu.uniquindio.clinica.modelo.factory to com.fasterxml.jackson.databind;

    exports co.edu.uniquindio.clinica.modelo.entidades;
    exports co.edu.uniquindio.clinica.app;
    exports co.edu.uniquindio.clinica.modelo.factory to com.fasterxml.jackson.databind;
}
