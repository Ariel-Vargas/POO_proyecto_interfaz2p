/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyecto2p.controller;

import ec.edu.espol.proyecto2p.App;
import ec.edu.espol.proyecto2p.modelo.Usuario;
import ec.edu.espol.proyecto2p.modelo.Vehiculo;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * 
 */
public class PerfilusuarioController implements Initializable {

    String correo_usuario;
    Usuario usuario;

    @FXML
    private TextField nombreuser;
    @FXML
    private TextField apellidouser;
    @FXML
    private TextField correouser;
    @FXML
    private TextField password;
    @FXML
    private TextField password1;
    @FXML
    private TextField orguser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            ArrayList<Usuario> lista = new ArrayList<>();
            lista= Usuario.readSer("usuario.ser");
            for(Usuario u: lista){          
                if(u.getCorreoe().equals(this.correo_usuario)){
                    usuario = u;
                    nombreuser.setText(u.getNombres());
                    apellidouser.setText(u.getApellidos());
                    correouser.setText(u.getCorreoe());
                    //password.setText(u.getContrasena());
                    orguser.setText(u.getOrganizacion());                    
                    break;
                }
            }
               
        });
    }    

    @FXML
    private void changePassword(MouseEvent event) {
        if (password1.getText().length() >0 ){
            if(usuario.getContrasena().equals(password.getText())){
                ArrayList<Usuario> lista = new ArrayList<>();
                lista= Usuario.readSer("usuario.ser");
                lista.remove(usuario);
                usuario.setContrasena(password1.getText());
                lista.add(usuario);
                Usuario.saveSer("usuario.ser", lista);
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Se ha cambiado la contrasena.");
                a.show();
                try {
                    FXMLLoader loader = App.loadFXML("login");
                    Scene sc = new Scene(loader.load(), 640, 480);
                    LoginController mc = loader.getController();
                    mc.setUsuario(this.correo_usuario);
                    Stage st = new Stage();
                    st.setScene(sc);
                    st.show();
                    Stage old = (Stage) correouser.getScene().getWindow();
                    old.close();
                } catch (IOException ex) {
                    Alert a1 = new Alert(Alert.AlertType.ERROR, "No se pudo cargar el archivo");
                    a.show();
                }

            }else{
                Alert a = new Alert(Alert.AlertType.ERROR, "La contrasena actual debe coincidir.");
                a.show();
            }
        }else{
            Alert a = new Alert(Alert.AlertType.ERROR, "Debe escribir una contrasena nueva y la anterior.");
            a.show();
        }
        
    }

    @FXML
    private void volver(MouseEvent event) {
        Button b = (Button)event.getSource();
        FXMLLoader loader;
        try {
            loader = App.loadFXML("menu");
            Scene sc = new Scene(loader.load(), 640, 480);
            MenuController mc = loader.getController();
            mc.setUsuario(this.correo_usuario);
            Stage st = new Stage();
            st.setScene(sc);
            st.show();
            Stage old = (Stage) b.getScene().getWindow();
            old.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }

    public void setUsuario(String correo){
        this.correo_usuario = correo;

    }
    
    
    
}
