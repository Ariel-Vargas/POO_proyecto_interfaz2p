
package ec.edu.espol.proyecto2p.modelo;




import java.util.Scanner;


public class Camioneta extends Auto{
    private String traccion;
    

    public Camioneta(int vidrios, String transmision, String traccion, String placa, String marca, String modelo, String tipoMotor, int año, double recorrido, String color, String tipoCombustible, double precio) {
        super(vidrios, transmision, placa, marca, modelo, tipoMotor, año, recorrido, color, tipoCombustible, precio);
        this.traccion = traccion;
    }
    
    public String getTraccion() {
        return traccion;
    }

    public void setTraccion(String traccion) {
        this.traccion = traccion;
    }

    @Override
    public void ingresarNuevoVehiculo(){
        Scanner sc= new Scanner(System.in);
        super.ingresarNuevoVehiculo();
        Camioneta c = new Camioneta(this.getVidrios(), this.getTransmision(),this.traccion, this.getPlaca(), this.getMarca(), this.getModelo(), this.getTipoMotor(), this.getAño(), this.getRecorrido(), this.getColor(), this.getTipoCombustible(), this.getPrecio());
    }  
}