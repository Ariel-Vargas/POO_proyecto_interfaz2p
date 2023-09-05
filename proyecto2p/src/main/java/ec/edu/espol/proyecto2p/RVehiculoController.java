/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyecto2p;
import ec.edu.espol.proyecto2p.App;
import ec.edu.espol.proyecto2p.modelo.Vehiculo;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.io.File;
import java.io.FileOutputStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;


/**
 * FXML Controller class
 *
 *
 */
public class RVehiculoController implements Initializable {

    String correo_usuario;

    @FXML
    private TextField placa;
    @FXML
    private TextField marca;
    @FXML
    private TextField modelo;
    @FXML
    private TextField tipoMotor;
    @FXML
    private TextField anio;
    @FXML
    private TextField recorrido;
    @FXML
    private TextField color;
    @FXML
    private TextField tipoCombustible;
    @FXML
    private TextField precio;
    @FXML
    private Button continuar;
    @FXML
    private Button regresar;
    @FXML
    private Label nombre_imagen;
    @FXML
    private TextField imagen_path;
    

    private ArrayList<Vehiculo> vehiculos = new ArrayList<>();
    @FXML
    private Button imagen;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void registrarVehiculo(ActionEvent event) {
       try{
            String placa1 = placa.getText();
            String marca1 = marca.getText();
            String modelo1 = modelo.getText();
            String tipoMotor1 = tipoMotor.getText();
            int anio1 = Integer.parseInt(anio.getText());
            double recorrido1 = Double.parseDouble(recorrido.getText());
            String color1 = color.getText();
            String tipoCombustible1 = tipoCombustible.getText();
            double precio1 = Double.parseDouble(precio.getText());
            String imagen= imagen_path.getText();
            if (imagen.length()==0){
                throw new NumberFormatException();
            }

            ArrayList<Vehiculo> vehiculos = Vehiculo.readSer("vehiculos.ser");
            for (Vehiculo v : vehiculos) {
                if (v.getPlaca().equals(placa1)) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Registro  inválido");
                    a.show();
                    return;
                }
            }
            Vehiculo nVehiculo = new Vehiculo(placa1, marca1, modelo1, tipoMotor1, anio1, recorrido1, color1, tipoCombustible1, precio1, imagen);
            vehiculos.add(nVehiculo);
            Vehiculo.saveSer("vehiculos.ser", vehiculos);

            Alert a = new Alert(Alert.AlertType.INFORMATION, "Registro válido");
            a.show();
            TextField[] allTextFields = {placa, marca, modelo, tipoMotor, anio, recorrido, color, tipoCombustible, precio, imagen_path} ;
            for (TextField textField : allTextFields) {
                textField.setText("");
            }
        }
        catch(NumberFormatException n)
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Ingresaste un valor incorrectamente, o no haz ingresado la imagen.");
            a.show();
        }
    }

    @FXML
    private void seleccionarImagen(ActionEvent event) {

        ArrayList<String> EXTENSION=new ArrayList<>();
        EXTENSION.add("*.png");
        EXTENSION.add("*.jpg");
        EXTENSION.add(".jpeg");
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("Imagenes PNG, JPG, JPEG", EXTENSION));
        File f = fc.showOpenDialog(null);
        if (f != null){
            nombre_imagen.setText(f.getName());            
            //imagen_path.setText(f.getAbsolutePath());
            try{
                imagen_path.setText(f.toURI().toURL().toExternalForm());
            }catch(Exception e){
                e.printStackTrace();

            }
            //f.toURI().toURL().toExternalForm();
            //try{
            //    FileOutputStream saveFile = new FileOutputStream(f.getAbsolutePath(), true);
//}
            
        }
    }

    @FXML
    private void regresar(ActionEvent event) {
        try {
            FXMLLoader loader = App.loadFXML("vendedor");
            Scene sc = new Scene(loader.load(), 640, 480);
            VendedorController mc = loader.getController();
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
