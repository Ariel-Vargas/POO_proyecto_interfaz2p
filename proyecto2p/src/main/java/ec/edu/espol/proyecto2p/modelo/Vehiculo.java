
package ec.edu.espol.proyecto2p.modelo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;
import java.lang.Integer;
import java.lang.Double;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Vehiculo implements Serializable{
    public static String NOMBRE_ARCHIVO = "Registros Vehiculos.txt";
    public static int POSICION_PLACA = 1;
    public static int POSICION_CORREO = 13;
    private String imagen;
    private String placa;
    private String marca;
    private String modelo;
    private String tipoMotor;
    private int año;
    private double recorrido;
    private String color;
    private String tipoCombustible;
    private double precio;
    private static final long serialVersionUID = 8799656478674716638L;
    private ArrayList<Oferta> listaOfertas;
    private Image imagen2;
    private ImageView imagen1;
    
    public Vehiculo(){
        
    }

    public Vehiculo(String placa, String marca, String modelo, String tipoMotor, int año, double recorrido, String color, String tipoCombustible, double precio, String imagen) {
        this.imagen = imagen;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tipoMotor = tipoMotor;
        this.año = año;
        this.recorrido = recorrido;
        this.color = color;
        this.tipoCombustible = tipoCombustible;
        this.precio = precio;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipoMotor() {
        return tipoMotor;
    }

    public void setTipoMotor(String tipoMotor) {
        this.tipoMotor = tipoMotor;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public double getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(double recorrido) {
        this.recorrido = recorrido;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipoCombustible() {
        return tipoCombustible;
    }

    public void setTipoCombustible(String tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return this.imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public ImageView getImagen1() {
        return this.imagen1;
    }

    public Image getImagen2() {
        return this.imagen2;
    }

    public void setImagen1(Image imagen1) {
        this.imagen2 = imagen1;
        ImageView imageView = new ImageView(imagen1);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(75);
        this.imagen1 = imageView;
    }

    
    public static void saveSer(String n, ArrayList<Vehiculo> vehiculos){
        try(ObjectOutputStream bo = new ObjectOutputStream(new FileOutputStream(n))){
            bo.writeObject(vehiculos);
        }catch(IOException e){}
    }
    
    public static ArrayList<Vehiculo> readSer(String n){
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(n))){
            vehiculos = (ArrayList<Vehiculo>)in.readObject();
        }catch(IOException e){
        }catch(ClassNotFoundException c){}
        
        return vehiculos;
    }
    
    
    
    
    
    
    public void ingresarNuevoVehiculo() {
        Scanner sc = new Scanner(System.in);
        boolean credencialesValidas = false;
        String correoElectronico = null;
        String clave = null;

        do {
            System.out.println("Ingrese su correo electrónico: ");
            correoElectronico = sc.nextLine();
            System.out.println("Ingrese su contraseña: ");
            clave = sc.nextLine();

            if (!Utilitarios.validarCredenciales(correoElectronico, clave, Vendedor.NOMBRE_ARCHIVO)) {        //validarlo
                System.out.println("Credenciales inválidas. No se permite aceptar ofertas.");
            } else {
                credencialesValidas = true;
            }
        } while (!credencialesValidas);

        
        String vidrios = "";
        String transmision = "";
        Vehiculo vehiculo = new Vehiculo();
        String traccion = "";
        String tipoVehiculo ="";
        do {
            System.out.println("Ingrese el tipo de vehí­culo (Moto - Auto - Camioneta): ");
        
            tipoVehiculo = sc.nextLine();
            tipoVehiculo = tipoVehiculo.strip().toLowerCase();

            if (!tipoVehiculo.equals("moto") && !tipoVehiculo.equals("auto") && !tipoVehiculo.equals("camioneta")){
                System.out.println("Tiene que elegir entre los 3 tipos de vehiculo: (Moto - Auto - Camioneta)\n");
            }else{
                break;
            }
        }while(!tipoVehiculo.equals("moto") && !tipoVehiculo.equals("auto") && !tipoVehiculo.equals("camioneta"));
        

        System.out.println("\nIngrese placa: ");
        String placa = sc.nextLine();

        boolean existeVehiculo = Utilitarios.validarValorArchivo(placa, 1, Vehiculo.NOMBRE_ARCHIVO);
        if (existeVehiculo){
            System.out.println("Un vehiculo con esta placa ya fue registrado.");
            Vendedor.opcionesVendedor();
        }

        System.out.println("Ingrese marca: ");
        String marca = sc.nextLine();

        System.out.println("Ingrese modelo: ");
        String modelo = sc.nextLine();

        System.out.println("Ingrese tipo de motor: ");
        String tipoMotor = sc.nextLine();

        System.out.println("Ingrese año:");
        String year=sc.nextLine();

        System.out.println("Ingrese recorrido:");
        String recorrido=sc.nextLine();
        
        System.out.println("Ingrese color: ");
        String color = sc.nextLine();

        System.out.println("Ingrese tipo de combustible: ");
        String tipoCombustible = sc.nextLine();

        System.out.println("Ingrese precio:");
        String precio=sc.nextLine();

        if (!tipoVehiculo.equals("moto")){
            System.out.println("Ingrese la cantidad de vidrios:");
            vidrios = sc.nextLine();
            
            System.out.println("Ingrese la transmision: ");
            transmision = sc.nextLine();
        }

        if (tipoVehiculo.equals("auto")){
            vehiculo= new Auto(Integer.parseInt(vidrios), transmision, placa, marca, modelo, tipoMotor, Integer.parseInt(year), Double.parseDouble(recorrido), color, tipoCombustible, Double.parseDouble(precio));
        } 

        if (tipoVehiculo.equals("camioneta")){
            System.out.println("Ingrese la traccion: ");
            traccion = sc.nextLine();

            vehiculo= new Camioneta(Integer.parseInt(vidrios), transmision, traccion, placa, marca, modelo, tipoMotor, Integer.parseInt(year), Double.parseDouble(recorrido), color, tipoCombustible, Double.parseDouble(precio));
        }
        
        if (tipoVehiculo.equals("moto")){
            vehiculo= new Vehiculo(placa, marca, modelo, tipoMotor, Integer.parseInt(year), Double.parseDouble(recorrido), color, tipoCombustible, Double.parseDouble(precio),"");
        }

        String registroVehiculo = tipoVehiculo + "|" + placa + "|" + marca + "|" + modelo + "|" 
                                    + tipoMotor + "|" + year + "|" + recorrido + "|" + color 
                                    + "|" + tipoCombustible + "|" + precio + "|" + vidrios + "|" + transmision + "|" + traccion+"|"+correoElectronico;
    
        Utilitarios.saveFile(Vehiculo.NOMBRE_ARCHIVO, registroVehiculo);
        Vendedor.opcionesVendedor();
    
        }

    public static boolean IsInteger(String text) {
        int v;
        try {
          v=Integer.parseInt(text);
          return true;
        } catch (NumberFormatException ex) {
            System.out.println("Ingresar un valor correcto (Numero Entero). ");
            return false;
        }
    }

    public static boolean IsDouble(String text) {
        double v;
        try {
          v=Double.parseDouble(text);
          return true;
        } catch (NumberFormatException ex) {
            System.out.println("Ingresar un valor correcto (Numero).");
            return false;
        }
    }

    public static Vehiculo cargarVehiculoArchivo(String registroVehiculo){
        String tokens[] = registroVehiculo.split("\\|");
        String placa = tokens[1];
        String marca = tokens[2];
        String modelo = tokens[3];
        String tipoMotor = tokens[4];
        int year = Integer.parseInt(tokens[5]);
        double recorrido = Double.parseDouble(tokens[6]);
        String color = tokens[7];
        String tipoCombustible = tokens[8];
        double precio = Double.parseDouble(tokens[9]);
        int vidrios = Integer.parseInt(tokens[10]);
        String transmision = tokens[11];
        if(transmision.isEmpty())
            transmision = null;
        String traccion = tokens[12];
        if(traccion.isEmpty())
            traccion = null;

        if (tokens[0].equals("moto")){
            Vehiculo vehiculo = new Vehiculo(placa, marca, modelo, tipoMotor, year, recorrido, color, tipoCombustible, precio,"");
            return vehiculo;
        } else if(tokens[0].equals("auto")){
            Vehiculo vehiculo = new Auto(vidrios, transmision, placa, marca, modelo, tipoMotor, year, recorrido, color, tipoCombustible, precio);
            return vehiculo;
        } else if (tokens[0].equals("camioneta")){
            Vehiculo vehiculo = new Camioneta(vidrios, transmision, traccion, placa, marca, modelo, tipoMotor, year, recorrido, color, tipoCombustible, precio);
            return vehiculo;
        }

        return null;

    }

}
