/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyecto2p.controller;

import ec.edu.espol.proyecto2p.App;
import ec.edu.espol.proyecto2p.modelo.Oferta;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ec.edu.espol.proyecto2p.modelo.Utilitarios;

/**
 * FXML Controller class
 *
 *
 */
public class AceptarOfertaController implements Initializable {

    @FXML
    private Button aceptaroferta;
    @FXML
    private Button regresar;
    @FXML
    private TableView<Oferta> tableview;
    @FXML
    private TableColumn<Oferta, String> comprador;
    @FXML
    private TableColumn<Oferta, String> correoComprador;
    @FXML
    private TableColumn<Oferta, String> vehiculo;
    @FXML
    private TableColumn<Oferta, String> placa;
    @FXML
    private TableColumn<Oferta, Double> precio;
    String asunto = "Oferta Aceptada";
    String cuerpo = "Su oferta ha sido aceptada";
    ArrayList<Oferta> listaOfertas;
    public String correo_usuario;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          Platform.runLater(() -> {
              
            for (Oferta o : this.listaOfertas) {

                placa.setCellValueFactory(new PropertyValueFactory<>("placa"));
                precio.setCellValueFactory(new PropertyValueFactory<>("precio"));
                correoComprador.setCellValueFactory(new PropertyValueFactory<>("correo"));
                comprador.setCellValueFactory(new PropertyValueFactory<>("nombres"));

            }

            tableview.setItems(FXCollections.observableArrayList(listaOfertas));
            tableview.getSortOrder().add(placa);
            tableview.getSortOrder().add(precio);
            tableview.getSortOrder().add(correoComprador);
            tableview.getSortOrder().add(comprador);

        });

    }    

    @FXML
    private void aceptarOferta(MouseEvent event) {
        
             Utilitarios.enviarConGmail(correoComprador, asunto, cuerpo);

        
             Alert a = new Alert(Alert.AlertType.INFORMATION, "Se ha enviado un correo al Comprador para indicar que ha aceptado su oferta");
             a.show();
    }

    @FXML
    private void regresar(MouseEvent event) {
        Button b = (Button)event.getSource();
         try {
            FXMLLoader loader = App.loadFXML("Vendedor");
            Scene sc = new Scene(loader.load(), 640, 480);
            VendedorController mc = loader.getController();
            Stage st = new Stage();
            st.setScene(sc);
            st.show();
            Stage old = (Stage) b.getScene().getWindow();
            old.close();
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "No se pudo cargar el archivo");
            a.show();
        }
    }

    public void setUsuario(String correo) {
        this.correo_usuario = correo;

    }
    
    }
    

