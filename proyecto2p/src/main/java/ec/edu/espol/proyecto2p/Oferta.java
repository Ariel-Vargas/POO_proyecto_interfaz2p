/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyecto2p;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ariel
 */
public class Oferta {
    public static String ASUNTO_CORREO = "Oferta por auto aceptada.";
    public static String CUERPO_CORREO = "Su oferta por un valor de VALOR, por el auto AUTO ha sido aceptada.";
    public static String VALOR = "VALOR";
    public static String AUTO = "AUTO";
    private ArrayList<Vehiculo> vehiculo;

    public Oferta() {
        this.vehiculo = new ArrayList<>();
    }
    
    
     public void menuOfertas(ArrayList<String> listaOfertas, Vehiculo vehiculo) {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        int opcion;
        int contador = 0;
        int cantidadOfertas = listaOfertas.size();
        do {
            String autoDescripcion = vehiculo.getMarca()+" "+vehiculo.getModelo()+" "+vehiculo.getTipoMotor();
            String ofertaActual = listaOfertas.get(contador);
            String tokens[] = ofertaActual.split("\\|");
            String correo = tokens[0];
            String precioOfertado = tokens[2];
            System.out.println("1. Siguiente Oferta");
            if (contador == 0){
                System.out.println("2. Aceptar Oferta");
            }else if(contador > 0){
                System.out.println("2. Anterior Oferta");
                System.out.println("3. Aceptar Oferta");
            }
            
            System.out.println("Escribe la opción que deseas: ");
            opcion = sc.nextInt();
            System.out.println("");

            if (contador == 0){
                if (opcion==2){
                    opcion = 3;
                }
            }

            if (contador ==cantidadOfertas){
                contador = 0;
            }
            switch (opcion) {
                case 1:
                    contador=contador + 1;
                    if (contador ==cantidadOfertas){
                        contador = 0;
                    }
                    break;
                case 2:
                    contador=contador - 1;
                    break;
            }
        } while (!salir);
    }
}
