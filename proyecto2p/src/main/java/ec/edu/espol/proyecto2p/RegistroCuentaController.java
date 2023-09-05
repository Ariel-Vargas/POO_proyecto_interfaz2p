/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyecto2p;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author ariel
 */
public class RegistroCuentaController implements Initializable {

    @FXML
    private TextField nombre;
    @FXML
    private TextField apellido;
    @FXML
    private TextField correo;
    @FXML
    private PasswordField contrasena;
    @FXML
    private Button aceptarcuenta;
    @FXML
    private Button salir;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void aceptar(MouseEvent event) {
        String cor = correo.getText();
        String cont = contrasena.getText();
        if(correo.getText().isEmpty() || contrasena.getText().isEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR, "No contiene información para registrar");
            a.show();
        }
        else{
            Usuario u = new Usuario(cor, cont);
            ArrayList<Usuario> lista = new ArrayList<>();
            lista.add(u);

            Usuario.saveSer("UsuarioRegistradoSer.txt", lista);
            
            try {
                FXMLLoader loader = App.loadFXML("login");
                Scene sc = new Scene(loader.load(), 640, 480);
                LoginController lc = loader.getController();
                App.setScene(sc);
            } catch (IOException ex) {
                Alert a = new Alert(Alert.AlertType.ERROR, "No se pudo cargar el archivo");
                a.show();
            }
        }
        
    }

    @FXML
    private void volver(MouseEvent event) {
        try {
            FXMLLoader loader = App.loadFXML("login");
            Scene sc = new Scene(loader.load(), 640, 480);
            LoginController lc = loader.getController();
            App.setScene(sc);
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "No se pudo cargar el archivo");
            a.show();
        }
    }
    
}
