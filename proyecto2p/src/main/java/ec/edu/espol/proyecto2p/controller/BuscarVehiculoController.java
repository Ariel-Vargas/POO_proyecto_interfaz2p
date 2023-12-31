/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyecto2p.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import ec.edu.espol.proyecto2p.App;
import ec.edu.espol.proyecto2p.modelo.Usuario;
import ec.edu.espol.proyecto2p.modelo.Vehiculo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 *
 */
public class BuscarVehiculoController implements Initializable {

    String correo_usuario;

    @FXML
    private TextField tipo;
    @FXML
    private TextField anio1;
    @FXML
    private TextField anio2;
    @FXML
    private TextField recorrido1;
    @FXML
    private TextField recorrido2;
    @FXML
    private TextField precio1;
    @FXML
    private TextField precio2;


    public void setUsuario(String correo){
        this.correo_usuario = correo;
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void siguiente(MouseEvent event) {
        Boolean todos_vacios = true;
        TextField[] allTextFields = {tipo, anio1, anio2, recorrido1, recorrido2, precio1, precio2};
        for (TextField textField : allTextFields) {
            if (textField.getText().length()>0){
                todos_vacios=false;  
                break;
            }
        }

        if (todos_vacios){
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Se debe ingresar al menos un parametro de busqueda.");
            a.show();
        }else{
            String v_tipo = null;
            double v_recorrido1=0.0;
            double v_recorrido2=0.0;
            int v_anio1= 0;
            int v_anio2= 0;
            double v_precio1= 0.0;
            double v_precio2= 0.0;
            try{
                if (tipo.getText().length()>0){
                    v_tipo = tipo.getText();
                }

                if (recorrido1.getText().length()>0){
                    v_recorrido1 = Double.parseDouble(recorrido1.getText());
                }
                if (recorrido2.getText().length()>0){
                    v_recorrido2 = Double.parseDouble(recorrido2.getText());
                }
                if (anio1.getText().length()>0){
                    v_anio1 = Integer.parseInt(anio1.getText());
                }
                if (anio2.getText().length()>0){
                    v_anio2 = Integer.parseInt(anio2.getText());
                }
                if (precio1.getText().length()>0){
                    v_precio1 = Double.parseDouble(precio1.getText());
                }
                if (precio2.getText().length()>0){
                    v_precio2 = Double.parseDouble(precio2.getText());
                }

                ArrayList<Vehiculo> lista = new ArrayList<>();
                ArrayList<Vehiculo> lista_buscada = new ArrayList<>();
                lista= Vehiculo.readSer("vehiculos.ser");
                for(Vehiculo v: lista){
                    boolean coincide_tipo = false;
                    boolean coincide_recorrido = false;
                    boolean coincide_anio = false;
                    boolean coincide_precio = false;
                    if (v_tipo != null){
                        if (v.getTipoMotor().equals(v_tipo)){
                            coincide_tipo = true;
                        }
                    }else{
                        coincide_tipo = true;
                    }
                    if (v_recorrido1 != 0.0 && v_recorrido2 != 0.0){
                        if (v.getRecorrido() >= v_recorrido1 && v.getRecorrido()<= v_recorrido2){
                            coincide_recorrido = true;
                        }
                    }else if(v_recorrido1 != 0.0){
                        if (v.getRecorrido() >= v_recorrido1){
                            coincide_recorrido = true;
                        }
                    }else if(v_recorrido2 != 0.0){
                        if (v.getRecorrido() <= v_recorrido2){
                            coincide_recorrido = true;
                        }
                    }else{
                        coincide_recorrido = true;
                    }


                    if (v_anio1 != 0 && v_anio2 != 0){
                        if (v.getAño() >= v_anio1 && v.getAño()<= v_anio2){
                            coincide_anio = true;
                        }
                    }else if(v_anio1 != 0){
                        if (v.getAño() >= v_anio1){
                            coincide_anio = true;
                        }
                    }else if(v_anio2 != 0){
                        if (v.getAño() <= v_anio2){
                            coincide_anio = true;
                        }
                    }else{
                        coincide_anio = true;
                    }

                    if (v_precio1 != 0.0 && v_precio2 != 0.0){
                        if (v.getAño() >= v_precio1 && v.getAño()<= v_precio2){
                            coincide_precio = true;
                        }
                    }else if(v_precio1 != 0.0){
                        if (v.getAño() >= v_precio1){
                            coincide_precio = true;
                        }
                    }else if(v_precio2 != 0.0){
                        if (v.getAño() <= v_precio2){
                            coincide_precio = true;
                        }
                    }else{
                        coincide_precio = true;

                    }

                    if (coincide_tipo && coincide_recorrido && coincide_precio && coincide_anio){
                        lista_buscada.add(v);
                    }

                    
                }
                System.out.println(lista_buscada.size());
                try{
                    FXMLLoader loader = App.loadFXML("ofertar_vehiculo");
                    Scene sc = new Scene(loader.load(), 640, 480);
                    OfertarVehiculoController mc = loader.getController();
                    mc.setVehiculos(lista_buscada);
                    mc.setUsuario(this.correo_usuario);
                    Stage st = new Stage();
                    st.setScene(sc);
                    st.show();
                    Stage old = (Stage) tipo.getScene().getWindow();
                    old.close();
                } catch (IOException e) {
                    Alert a = new Alert(Alert.AlertType.ERROR, "No se pudo cargar el archivo");
                    a.show();
                    e.printStackTrace();
                }
                
                
            }
            catch(NumberFormatException n)
            {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Ingresaste un valor incorrectamente.");
                a.show();
            }
        }
        
    }
    
}
