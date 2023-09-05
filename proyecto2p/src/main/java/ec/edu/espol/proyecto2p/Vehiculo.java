package ec.edu.espol.proyecto2p;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class Vehiculo implements Serializable{
    
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

   

public Vehiculo(String placa, String marca, String modelo, String tipoMotor, int año, double recorrido, String color, String tipoCombustible, double precio) {
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
    
    
}
