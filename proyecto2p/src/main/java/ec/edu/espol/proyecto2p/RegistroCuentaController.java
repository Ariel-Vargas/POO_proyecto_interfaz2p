package ec.edu.espol.proyecto2p.controller;

import ec.edu.espol.proyecto2p.App;
import ec.edu.espol.proyecto2p.modelo.Usuario;
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
 
 */
public class RegistroCuentaController implements Initializable {

    String correo_usuario;

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
    @FXML
    private TextField organizacion;

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
            
            ArrayList<Usuario> usuarios = Usuario.readSer("usuario.ser");
        
            for(Usuario u: usuarios){
                if(u.getCorreoe().equals(cor)){
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Este correo ya está registrado");
                    a.show();
                    correo.setText("");
                    contrasena.setText("");
                    return;                
                }
            }
            
            Usuario us = new Usuario(cor,cont,nombre.getText(),apellido.getText(),organizacion.getText());
            usuarios.add(us);
            Usuario.saveSer("usuario.ser", usuarios);
//            Usuario.saveSer("UsuarioRegistradoSer.txt", usuarios);
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Registro exitoso");
            a.show();
            correo.setText("");
            contrasena.setText("");
        
                       
            try {
                FXMLLoader loader = App.loadFXML("login");
                Scene sc = new Scene(loader.load(), 640, 480);
                LoginController lc = loader.getController();
                App.setScene(sc);
            } catch (IOException ex) {
                Alert a1 = new Alert(Alert.AlertType.ERROR, "No se pudo cargar el archivo");
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

    public void setUsuario(String correo){
        this.correo_usuario = correo;

    }
    
}
