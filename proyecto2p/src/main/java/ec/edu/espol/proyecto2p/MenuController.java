/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyecto2p;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ariel
 */
public class MenuController implements Initializable {

    @FXML
    private ComboBox<Integer> opcion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        opcion.getItems().addAll(1, 2, 3, 4);
        // TODO
    }

    @FXML
    private void aceptar(MouseEvent event) {
        Button b = (Button)event.getSource();
        int n = opcion.getValue();
        
        try{
            switch(n){
                case 1: {
                    try {
                        FXMLLoader loader = App.loadFXML("registro_usuario");
                        Scene sc = new Scene(loader.load(), 640, 480);
                        Registro_usuarioController ru = loader.getController();
                        Stage st = new Stage();
                        st.setScene(sc);
                        st.show();
                        Stage old = (Stage)b.getScene().getWindow();
                        old.close();
                    } catch (IOException ex) {
                        Alert a = new Alert(Alert.AlertType.ERROR, "No se pudo cargar el archivo");
                        a.show();
                    }
                    break;
                }

                case 2:{
                    try {
                        FXMLLoader loader = App.loadFXML("registro_vehiculo");
                        Scene sc = new Scene(loader.load(), 640, 480);
                        RVehiculoController rv = loader.getController();
                        Stage st = new Stage();
                        st.setScene(sc);
                        st.show();
                        Stage old = (Stage)b.getScene().getWindow();
                        old.close();
                    } catch (IOException ex) {
                        Alert a = new Alert(Alert.AlertType.ERROR, "No se pudo cargar el archivo");
                        a.show();
                    }
                    break;
                }

                case 3:{
                    try {
                        FXMLLoader loader = App.loadFXML("buscarVehiculo");
                        Scene sc = new Scene(loader.load(), 640, 480);
                        BuscarVehiculoController bv = loader.getController();
                        Stage st = new Stage();
                        st.setScene(sc);
                        st.show();
                        Stage old = (Stage)b.getScene().getWindow();
                        old.close();
                    } catch (IOException ex) {
                        Alert a = new Alert(Alert.AlertType.ERROR, "No se pudo cargar el archivo");
                        a.show();
                    }
                    break;
                }

                case 4:{
                    try {
                        FXMLLoader loader = App.loadFXML("ofertarVehiculo");
                        Scene sc = new Scene(loader.load(), 640, 480);
                        OfertarVehiculoController ov = loader.getController();
                        Stage st = new Stage();
                        st.setScene(sc);
                        st.show();
                        Stage old = (Stage)b.getScene().getWindow();
                        old.close();
                    } catch (IOException ex) {
                        Alert a = new Alert(Alert.AlertType.ERROR, "No se pudo cargar el archivo");
                        a.show();
                    }
                    break;
                }
                default:{
                    Alert a = new Alert(Alert.AlertType.ERROR, "Opcion incorrecta");
                    a.show();
                }
            }
        }
        catch(NullPointerException np){
            np.getStackTrace();
            Alert a = new Alert(Alert.AlertType.ERROR, "Elija una opción");
            a.show();
        }
        
        
    }
}
