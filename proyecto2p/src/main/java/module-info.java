module ec.edu.espol.proyecto2p {
    requires jakarta.mail;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    
    opens ec.edu.espol.proyecto2p to javafx.fxml;
    exports ec.edu.espol.proyecto2p;
    opens ec.edu.espol.proyecto2p.modelo to javafx.fxml;
    exports ec.edu.espol.proyecto2p.modelo;
    opens ec.edu.espol.proyecto2p.controller to javafx.fxml;
    exports ec.edu.espol.proyecto2p.controller;
}
