/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyecto2p.controller;

import ec.edu.espol.proyecto2p.App;
import ec.edu.espol.proyecto2p.controller.MenuController;
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
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * 
 */
public class LoginController implements Initializable {

    String correo_usuario;


    @FXML
    private TextField correo;
    @FXML
    private PasswordField contrasena;
    @FXML
    private Button continuar;
    @FXML
    private Button salir;
    @FXML
    private Button registro;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Continuar(MouseEvent event) {
        Button b = (Button)event.getSource();
        String correoe = correo.getText();
        String contra = contrasena.getText();
        
        ArrayList<Usuario> lista = new ArrayList<>();
        lista= Usuario.readSer("Usuario.ser");
        boolean logged = false;
        for(Usuario u: lista){           
            if(u.getCorreoe().equals(correoe) && u.getContrasena().equals(contra)){
                try {
                    FXMLLoader loader = App.loadFXML("menu");
                    Scene sc = new Scene(loader.load(), 640, 480);
                    MenuController mc = loader.getController();
                    mc.setUsuario(correoe);
                    Stage st = new Stage();
                    st.setScene(sc);
                    st.show();
                    Stage old = (Stage)b.getScene().getWindow();
                    old.close();
                    Alert a1 = new Alert(Alert.AlertType.INFORMATION, "Inicio de sesion exitoso");
                    a1.show();
                } catch (IOException ex) {
                    Alert a = new Alert(Alert.AlertType.ERROR, "No se pudo cargar el archivo");
                    a.show();
                    ex.printStackTrace();
                }
                logged = true;
                break;
            }
        }
        if (!logged){
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Usuario Incorrecto");
            a.show();
            limpiezaDatos();
        }
        

    }
    
    private void limpiezaDatos(){
        correo.setText("");
        contrasena.setText("");
    }

    @FXML
    private void Salir(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void registrar(MouseEvent event) {
        try {
            FXMLLoader loader = App.loadFXML("registro_cuenta");
            Scene sc = new Scene(loader.load(), 640, 480);
            RegistroCuentaController rc = loader.getController();
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
