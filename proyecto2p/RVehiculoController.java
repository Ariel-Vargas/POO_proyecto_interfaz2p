/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyecto2p;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Shirley Aragon
 */
public class RVehiculoController implements Initializable {

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

    private ArrayList<Vehiculo> vehiculos = new ArrayList<>();

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

            ArrayList<Vehiculo> vehiculos = Vehiculo.readSer("vehiculos.ser");
            for (Vehiculo v : vehiculos) {
                if (v.getPlaca().equals(placa1)) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Registro  inválido");
                    a.show();
                    return;
                }
            }
            Vehiculo nVehiculo = new Vehiculo(placa1, marca1, modelo1, tipoMotor1, anio1, recorrido1, color1, tipoCombustible1, precio1);
            vehiculos.add(nVehiculo);
            Vehiculo.saveSer("vehiculos.ser", vehiculos);

            Alert a = new Alert(Alert.AlertType.INFORMATION, "Registro válido");
            a.show();
            placa.setText("");
        }
        catch(NumberFormatException n)
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Ingresaste un valor incorrectamente");
            a.show();
        }
    }

}
