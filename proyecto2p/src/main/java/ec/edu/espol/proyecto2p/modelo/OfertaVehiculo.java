package ec.edu.espol.proyecto2p.modelo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class OfertaVehiculo {
    private double precio;
    private String placa;
    private String correo;

    public OfertaVehiculo(String placa, double precio, String correo){
        this.placa = placa;
        this.precio = precio;
        this.correo = correo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

     public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    

    public static void saveSer(String n, ArrayList<OfertaVehiculo> ofertas){
        try(ObjectOutputStream bo = new ObjectOutputStream(new FileOutputStream(n))){
            bo.writeObject(ofertas);
        }catch(IOException e){}
    }
    
    public static ArrayList<OfertaVehiculo> readSer(String n){
        ArrayList<OfertaVehiculo> ofertas = new ArrayList<>();
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(n))){
            ofertas = (ArrayList<OfertaVehiculo>)in.readObject();
        }catch(IOException e){
        }catch(ClassNotFoundException c){}
        
        return ofertas;
    }
}
