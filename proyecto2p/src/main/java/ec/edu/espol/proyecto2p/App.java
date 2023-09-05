package ec.edu.espol.proyecto2p;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.input.MouseEvent;

/**
 * JavaFX App
 */
ublic class App extends Application {

    private static Scene scene;
    private static Stage st;

    @Override
    public void start(Stage stage) throws IOException {
        st = stage;
        scene = new Scene(loadFXML("login").load(), 640, 480);
        stage.setScene(scene);
        stage.setTitle("COMPRA Y VENTA DE VEHICULOS");
        stage.setResizable(false);
        
        stage.show();
        ArrayList<Usuario> user = new ArrayList<>();
        
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml).load());
    }

    public static FXMLLoader loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader;
    }
    
    public static void setScene(Scene sc){
        st.setScene(sc);
        
    }

    public static void main(String[] args) {
        launch();
    }

}
