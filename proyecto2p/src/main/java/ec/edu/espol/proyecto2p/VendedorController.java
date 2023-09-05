/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyecto2p.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ec.edu.espol.proyecto2p.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *

 */
public class VendedorController implements Initializable {

    String correo_usuario;

    @FXML
    private ComboBox<Integer> opcion;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        opcion.getItems().addAll(1,2,3);
    }    

    @FXML
    private void opcion(MouseDragEvent event) {

    }

    @FXML
    private void siguiente(MouseEvent event) {
        Button b = (Button)event.getSource();
        int valor = (int)opcion.getValue();
        if(valor==1){
            try {
                FXMLLoader loader = App.loadFXML("registro_vehiculo");
                Scene sc = new Scene(loader.load(), 640, 480);
                RVehiculoController mc = loader.getController();
                Stage st = new Stage();
                st.setScene(sc);
                st.show();
                Stage old = (Stage) b.getScene().getWindow();
                old.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        
        }else if(valor==2){
            try {
                FXMLLoader loader = App.loadFXML("aceptar_oferta");
                Scene sc = new Scene(loader.load(), 640, 480);
                AceptarOfertaController mc = loader.getController();
                Stage st = new Stage();
                st.setScene(sc);
                st.show();
                Stage old = (Stage) b.getScene().getWindow();
                old.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }else{
            try {
                FXMLLoader loader = App.loadFXML("menu");
                Scene sc = new Scene(loader.load(), 640, 480);
                MenuController mc = loader.getController();
                Stage st = new Stage();
                st.setScene(sc);
                st.show();
                Stage old = (Stage) b.getScene().getWindow();
                old.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void setUsuario(String correo){
        this.correo_usuario = correo;

    }
    
    
}
