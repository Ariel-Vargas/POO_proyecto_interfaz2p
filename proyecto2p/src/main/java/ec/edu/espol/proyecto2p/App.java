package ec.edu.espol.proyecto2p;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
        /*
        Scanner sc = new Scanner(System.in);
        String correo; 
        String cont;
        ArrayList<Usuario> us = new ArrayList<>();
        for(int i=0; i<2; i++){
            System.out.println("Ingrese correo electrónico: ");
            correo = sc.nextLine();
            System.out.println("Ingrese contraseña: ");
            cont = sc.nextLine();
            Usuario u = new Usuario(correo, cont);
            us.add(u);
        }
        
        Usuario.saveSer("UsuarioSer.txt", us);
        
        ArrayList<Usuario> lista = Usuario.readSer("UsuarioSer.txt");
        System.out.println(lista);
        */
    }

}