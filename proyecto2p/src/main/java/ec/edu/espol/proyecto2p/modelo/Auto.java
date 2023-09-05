
package ec.edu.espol.proyecto2p.modelo;

import ec.edu.espol.proyecto2p.modelo.Vehiculo;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;




public class Auto extends Vehiculo {

    private int vidrios;
    private String transmision;

    public Auto(int vidrios, String transmision, String placa, String marca, String modelo, String tipoMotor, int año, double recorrido, String color, String tipoCombustible, double precio) {
        super(placa, marca, modelo, tipoMotor, año, recorrido, color, tipoCombustible, precio,"");
        this.vidrios = vidrios;
        this.transmision = transmision;
    }

    public int getVidrios() {
        return vidrios;
    }

    public void setVidrios(int vidrios) {
        this.vidrios = vidrios;
    }

    public String getTransmision() {
        return transmision;
    }

    public void setTranmision(String tranmision) {
        this.transmision = tranmision;
    }

    @Override
    public void ingresarNuevoVehiculo() {
        Scanner sc = new Scanner(System.in);
        super.ingresarNuevoVehiculo();  //llama al super para validar
        Auto a = new Auto(vidrios, transmision, this.getPlaca(), this.getMarca(), this.getModelo(), this.getTipoMotor(), this.getAño(), this.getRecorrido(), this.getColor(), this.getTipoCombustible(), this.getPrecio());
    }
  
    String sf = this.vidrios + "|" + this.transmision;
    
}

