/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyecto2p.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


import ec.edu.espol.proyecto2p.modelo.Vehiculo;
import ec.edu.espol.proyecto2p.modelo.OfertaVehiculo;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ec.edu.espol.proyecto2p.App;
/**
 * FXML Controller class
 *
 * 
 */
public class OfertarVehiculoController implements Initializable {

    String correo_usuario;

    @FXML
    private Button siguiente;
    @FXML
    private Button regresar;

    @FXML
    private TableView<Vehiculo> table;
    @FXML
    private TableColumn<Vehiculo, String> placa;
    @FXML
    private TableColumn<Vehiculo, ImageView> imagen1;
    @FXML
    private TableColumn<Vehiculo, String> marca;
    @FXML
    private TableColumn<Vehiculo, String> motor;
    @FXML
    private TableColumn<Vehiculo, String> modelo;
    @FXML
    private TableColumn<Vehiculo, Integer> anio;
    @FXML
    private TableColumn<Vehiculo, Double> recorrido;
    @FXML
    private TableColumn<Vehiculo, Double> precio;

    @FXML
    private TextField placa_oferta;
    @FXML
    private TextField precio_oferta;


    ArrayList<Vehiculo> lista_vehiculos;

    int vehiclesNumber = 0;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Platform.runLater(() -> {
            for(Vehiculo v: this.lista_vehiculos){
                Image imagen_vehiculo = null;
                if (v.getImagen().length() > 0){
                    imagen_vehiculo = new Image(v.getImagen());
                }
                else{
                    try{
                        String a = new File("default.png").toURI().toURL().toExternalForm();
                        imagen_vehiculo = new Image(a);
                    }   
                    catch(Exception e){
                        e.printStackTrace();

                    }
                    
                }
                v.setImagen1(imagen_vehiculo);
                imagen1.setCellValueFactory(new PropertyValueFactory<>("imagen1"));
                placa.setCellValueFactory(new PropertyValueFactory<>("placa"));
                marca.setCellValueFactory(new PropertyValueFactory<>("marca"));
                modelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
                motor.setCellValueFactory(new PropertyValueFactory<>("tipoMotor"));
                anio.setCellValueFactory(new PropertyValueFactory<>("a\u00F1o"));
                recorrido.setCellValueFactory(new PropertyValueFactory<>("recorrido"));
                precio.setCellValueFactory(new PropertyValueFactory<>("precio"));
            }
            //Vehiculo[] vehiculos = {}
            table.setItems(FXCollections.observableArrayList(lista_vehiculos));
            table.getSortOrder().add(precio);
            table.getSortOrder().add(anio);
            vehiclesNumber = lista_vehiculos.size();

               
        });
        
    }    

    @FXML
    private void siguiente(ActionEvent event) {

        if (vehiclesNumber > 0 && placa_oferta.getText().length()>0 && precio_oferta.getText().length()>0){
            boolean placa_encontrada = false;
            for (Vehiculo v: this.lista_vehiculos){
                if (placa_oferta.getText().equals(v.getPlaca())){
                    placa_encontrada = true;
                    break;
                }
            }
            
            if (placa_encontrada){
                String placa_oferta_s = placa_oferta.getText();
                double precio_oferta_s = Double.parseDouble(precio_oferta.getText());
                OfertaVehiculo ofertaV = new OfertaVehiculo(placa_oferta_s, precio_oferta_s, this.correo_usuario);
                ArrayList<OfertaVehiculo> ofertas = OfertaVehiculo.readSer("Ofertas.ser");
                ofertas.add(ofertaV);
                OfertaVehiculo.saveSer("Ofertas.ser", ofertas);

                Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Se ha registrado su oferta por el valor de: " + precio_oferta_s +", placa: "+ placa_oferta_s);
                a.show();
            }else{
                Alert a = new Alert(Alert.AlertType.ERROR, "Esta placa no esta en la lista.");
                a.show();
            }
            
        }else{
            Alert a = new Alert(Alert.AlertType.ERROR, "Se tiene que colocar una placa y el precio a ofertar.");
            a.show();
        }

        

    }    

    public void setVehiculos(ArrayList<Vehiculo> lista_vehiculos){
        this.lista_vehiculos = lista_vehiculos;

    }

    @FXML
    private void regresar(ActionEvent event) {
        try {
            FXMLLoader loader = App.loadFXML("comprador");
            Scene sc = new Scene(loader.load(), 640, 480);
            CompradorController mc = loader.getController();
            Stage st = new Stage();
            st.setScene(sc);
            st.show();
            Stage old = (Stage) regresar.getScene().getWindow();
            old.close();
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "No se pudo cargar el archivo");
            a.show();
        }
    }  

    public void setUsuario(String correo){
        this.correo_usuario = correo;

    }
    
}
