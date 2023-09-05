/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyecto2p;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author ariel
 */
public class ComprarVehiculoController implements Initializable {

    @FXML
    private ImageView imvVeh;
    @FXML
    private HBox hbotones;
    @FXML
    private VBox vbotones;
    
    private ArrayList<String> listaOfertas;
    private int contador = 3; // tamaño de la lista de ofertas
    public int i; // indice

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // dar valores a la lista "listaOfertas"
        i = 0;
        menu();
    
    }

    private void seguir(Button b){
        String text = b.getText();
        if(text.equals("Siguiente")){
            b.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler(){
                @Override
                public void handle(Event t) {
                    i = i + 1;
                    menu();   
                }    
            });
        }
        else{
            if(text.equals("Anterior")){
                b.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler(){
                    @Override
                    public void handle(Event t) {
                        i = i - 1;
                        menu();   
                    }    
                });
            }
            else{
                b.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler(){
                    @Override
                    public void handle(Event t) {
                        SendCorreo.enviarConGMail(correo, Oferta.ASUNTO_CORREO, Oferta.CUERPO_CORREO.replaceAll(Oferta.AUTO, autoDescripcion).replaceAll(Oferta.VALOR, String.valueOf(precioOfertado)));
                    }    
                });
            }
        }
    }
    private void menu(){
        vbotones.getChildren().clear();
        String s = listaOfertas.get(i);
        Label l = new Label();
        l.setText(s);
        vbotones.getChildren().add(l);
        vbotones.getChildren().clear();
        if(i == 0 && listaOfertas.size() == 1){
            Button b1 = new Button();
            b1.setText("Aceptar Oferta");
            hbotones.getChildren().add(b1);
            seguir(b1);
        }
        else{
            if(i == 0 && listaOfertas.size() != 1){
                Button b1 = new Button();
                b1.setText("Siguiente");
                Button b2 = new Button();
                b2.setText("Aceptar Oferta");
                hbotones.getChildren().add(b1);
                hbotones.getChildren().add(b2);
                seguir(b1);
                seguir(b2);
            }
            else{
                if(i == contador && listaOfertas.size() != 1){
                    Button b1 = new Button();
                    b1.setText("Anterior");
                    Button b2 = new Button();
                    b2.setText("Aceptar Oferta");
                    hbotones.getChildren().add(b1);
                    hbotones.getChildren().add(b2);
                    seguir(b1);
                    seguir(b2);
                }
                else{
                    Button b1 = new Button();
                    b1.setText("Siguiente");
                    Button b2 = new Button();
                    b2.setText("Anterior");
                    Button b3 = new Button();
                    b3.setText("Aceptar Oferta");
                    hbotones.getChildren().add(b1);
                    hbotones.getChildren().add(b2);
                    hbotones.getChildren().add(b3);
                    seguir(b1);
                    seguir(b2);
                    seguir(b3);
                }
            }
            
        }
    }
    
}
