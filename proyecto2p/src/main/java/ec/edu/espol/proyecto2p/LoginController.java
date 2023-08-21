/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyecto2p;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class LoginController implements Initializable {


    @FXML
    private TextField correo;
    @FXML
    private PasswordField contrasena;
    @FXML
    private Button continuar;
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
    private void Continuar(MouseEvent event) {
        String correoe = correo.getText();
        String contra = contrasena.getText();
        
        ArrayList<Usuario> lista = Usuario.readSer("UsuarioSer.txt");
        
        for(Usuario u: lista){
            if(u.getCorreoe().equals(correoe) && u.getContrasena().equals(contra)){
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Usuario Valido");
                a.show();
                correo.setText("");
                contrasena.setText("");
                break;
            }else{
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Usuario Incorrecto");
                a.show();
                correo.setText("");
                contrasena.setText("");
                break;
            }
        }   
    }

    @FXML
    private void Salir(MouseEvent event) {
        System.exit(0);
    }
    
}
