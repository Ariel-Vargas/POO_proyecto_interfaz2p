/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyecto2p.controller;

import ec.edu.espol.proyecto2p.modelo.Usuario;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 
 */
public class Registro_usuarioController implements Initializable {

    String correo_usuario;

    @FXML
    private TextField user;
    @FXML
    private TextField password;
    @FXML
    private Button registrar;

    @FXML
    private TextField nombre;
    @FXML
    private TextField apellido;
    @FXML
    private TextField organizacion;
    
    ArrayList<Usuario> usuarios = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Registrar(MouseEvent event) {
        
        String correonuevo = user.getText();
        String contrasenia = password.getText();
        
        ArrayList<Usuario> usuarios = Usuario.readSer("usuario.ser");
        
        for(Usuario u: usuarios){
            if(u.getCorreoe().equals(correonuevo)){
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Este correo ya está registrado");
                a.show();
                user.setText("");
                password.setText("");
                return;                
            }
        }
        Usuario us = new Usuario(correonuevo,contrasenia, nombre.getText(), apellido.getText(), organizacion.getText());
        usuarios.add(us);
        Usuario.saveSer("usuario.ser", usuarios);
        Alert a = new Alert(Alert.AlertType.INFORMATION, "Registro exitoso");
        a.show();
        user.setText("");
        password.setText("");

    }

    public void setUsuario(String correo){
        this.correo_usuario = correo;

    }
}   
    

    

