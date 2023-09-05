/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyecto2p.modelo;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Scanner;

public class Vendedor {

    public static String NOMBRE_ARCHIVO = "Registros Vendedor.txt";

    private String nombre;
    private String apellidos;
    private String organizacion;
    private String correoElectronico;
    private String clave;
    private ArrayList<Vehiculo> vehiculo;

    public Vendedor() {
    }

    public Vendedor(String nombre, String apellidos, String organizacion, String correoElectronico, String clave) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.organizacion = organizacion;
        this.correoElectronico = correoElectronico;
        this.clave = clave;
        this.vehiculo = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public ArrayList<Vehiculo> getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(ArrayList<Vehiculo> vehiculo) {
        this.vehiculo = vehiculo;
    }

    //Opciones del Vendedor
    public static void opcionesVendedor() {
        Scanner sc = new Scanner(System.in);
        int regresar = 0;
        int opcion;
        do {
            if (regresar == 1){
                System.exit(0);
            }
            System.out.println("1. Registrar un nuevo vendedor");
            System.out.println("2. Registrar un nuevo vehiculo");
            System.out.println("3. Aceptar Oferta");
            System.out.println("4. Regresar");

            System.out.println("Escribe la opción que deseas: ");
            opcion = sc.nextInt();
     
            System.out.println("");

            switch (opcion) {
                case 1:
                    Vendedor vendedor = new Vendedor();
                    vendedor.registrarNuevoVendedor();
                    regresar = 1;
                    break;
                case 2:
                    Vehiculo vehiculo = new Vehiculo();
                    vehiculo.ingresarNuevoVehiculo();
                    regresar = 1;
                    break;
                case 3:
                    Oferta o = new Oferta();
                    o.aceptarOferta();
                    regresar = 1;
                    break;
                case 4:
                    System.out.println(" ");
                    Utilitarios.menuOpciones();
                    regresar = 2;
                    break;
                default:
                    System.out.println("Solo números entre 1 y 4");
            }
        } while (regresar!=2);
    }

    public void registrarNuevoVendedor() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese sus nombres: ");
        String nombre = sc.nextLine();
        System.out.println("Ingrese los apellidos: ");
        String apellido = sc.nextLine();
        System.out.println("Ingrese la Organización: ");
        String organizacion = sc.nextLine();
        System.out.println("Ingrese su correo electrónico: ");
        String correoElectronico = sc.nextLine();

        boolean existeCorreo = Utilitarios.validarValorArchivo(correoElectronico, 3, Vendedor.NOMBRE_ARCHIVO);
        if (existeCorreo){
            System.out.println("Un vendedor con este correo ya fue registrado.");
            System.out.println("");
            opcionesVendedor();
        }

        //clave método hash
        System.out.println("Ingrese su clave: ");
        String clave = sc.nextLine();
        clave = Utilitarios.getSHA256(clave);

        String registroVendedor = nombre + "|" + apellido + "|" + organizacion + "|" + correoElectronico + "|" + clave;
        Utilitarios.saveFile(Vendedor.NOMBRE_ARCHIVO, registroVendedor);
        opcionesVendedor();

    }

}

        