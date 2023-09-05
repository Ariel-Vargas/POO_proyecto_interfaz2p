package ec.edu.espol.proyecto2p.modelo;

import ec.edu.espol.proyecto2p.modelo.Vehiculo;
import ec.edu.espol.proyecto2p.modelo.Utilitarios;
import ec.edu.espol.proyecto2p.modelo.Vendedor;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;





public class Oferta {
    
    public static String NOMBRE_ARCHIVO = "Registros Ofertas.txt";
    public static int POSICION_PLACA = 1;
    public static String ASUNTO_CORREO = "Oferta por auto aceptada.";
    public static String CUERPO_CORREO = "Su oferta por un valor de VALOR, por el auto AUTO ha sido aceptada.";
    public static String VALOR = "VALOR";
    public static String AUTO = "AUTO";

    private ArrayList<Vehiculo> vehiculo;
    private Comprador comprador;

    public Oferta() {
        this.vehiculo = new ArrayList<>();
    }

    public ArrayList<Vehiculo> getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(ArrayList<Vehiculo> vehiculo) {
        this.vehiculo = vehiculo;
    }

    

    public void aceptarOferta() {
        Scanner sc = new Scanner(System.in);
        boolean credencialesValidas = false;
        String c_e;
        String contra;
        do {
            System.out.println("Ingrese su correo electronico: ");
            c_e = sc.nextLine();

            System.out.println("Ingrese su clave: ");
            contra = sc.nextLine();

            if (!Utilitarios.validarCredenciales(c_e, contra, Vendedor.NOMBRE_ARCHIVO)) {        //validarlo
                System.out.println("Credenciales inválidas");
            } else {
                credencialesValidas = true;
            }
        } while (!credencialesValidas);
        
        if(Utilitarios.validarCredenciales(c_e, contra, Vendedor.NOMBRE_ARCHIVO)){
            ArrayList<String> cadena = Utilitarios.readFile("Registros Ofertas.txt");
            
            int contador = 0;
            int opcion;
            String des;
            String autoDescripcion;
            boolean salir = false;

            do{
                if(contador < cadena.size()){
                    String letter = cadena.get(contador);
                    String[] val = letter.split("\\|");
                    //camioneta|DGU-712|Honda|Deportivo|Hibrido|2019|2000|Rojo|Gasolina|50260|6|Manual|Doble|vargasariel661@gmail.com|50000
                    des = "Precio Ofertado: "+val[14]+", Tipo :"+val[0]+", Placa: "+val[1]+", Marca: "+val[2]+", Modelo: "+val[3]+", Motor: "+val[4]+", Año: "+val[5]+", Recorrido: "+val[6]+", Color: "+val[7]+ ", Combustible: "+ val[8]+ ", Precio: "+val[9];
                    autoDescripcion = "Marca: "+val[2]+", Modelo: "+val[3]+", Tipo de motor: "+val[4];
                    System.out.println(des);

                    if(contador == 0 && cadena.size() > 1){
                        System.out.println("1. Siguiente Oferta");
                        System.out.println("2. Aceptar Oferta");
                        System.out.println("3. Regresar");
                    }
                    else if(contador == cadena.size()-1 && cadena.size() > 1){
                        System.out.println("1. Anterior Oferta");
                        System.out.println("2. Aceptar Oferta");
                        System.out.println("3. Regresar");
                    }
                    else if(contador == cadena.size()-1){
                        System.out.println("1. Aceptar Oferta");
                        System.out.println("2. Regresar");
                    }
                    else{
                        System.out.println("1. Siguiente Oferta");
                        System.out.println("2. Anterior Oferta");
                        System.out.println("3. Aceptar Oferta");
                        System.out.println("4. Regresar");
                    }

                    System.out.println("Elija una opcion: ");
                    opcion = sc.nextInt();

                    if(contador == 0 && cadena.size() > 1){
                        if(opcion == 2)
                            opcion = 3;
                        else if(opcion == 3)
                            opcion = 4;
                    }
                    else if(contador == cadena.size()-1 && cadena.size() > 1){
                        if(opcion == 1)
                            opcion = 2;
                        else if(opcion == 2)
                            opcion = 3;
                        else
                            opcion = 4;
                    }
                    else if(contador == cadena.size()-1){
                        if(opcion == 1)
                            opcion = 3;
                        else
                            opcion = 4;
                    }

                    switch(opcion){
                        case 1:
                            contador += 1;
                            break;
                        case 2: 
                            contador -= 1;
                            break;
                        case 3: 
                            Utilitarios.eliminarValoresArchivo(letter, Vehiculo.POSICION_PLACA, Vehiculo.NOMBRE_ARCHIVO);
                            //Utilitarios.enviarConGMail(val[13], Oferta.ASUNTO_CORREO, Oferta.CUERPO_CORREO.replaceAll(Oferta.AUTO, autoDescripcion).replaceAll(Oferta.VALOR, String.valueOf(val[14])));
                            System.out.println("Oferta Aceptada");
                            salir = true;
                            break;
                        case 4:
                            salir = true;
                            break;
                        default: 
                            System.out.println("Solo los numeros indicados en el menú");
                    }

                }
            }while(!salir);
        }
    }
    
    
    public static void guardarPrecioOfertado(String registro){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el precio ofertado: ");
        String p_o = sc.nextLine();
        String reg = registro + "|" + p_o;
        Utilitarios.saveFile("Registros Ofertas.txt", reg);
    }
    
}

