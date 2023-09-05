
package ec.edu.espol.proyecto2p.modelo;



import ec.edu.espol.proyecto2p.modelo.Oferta;
import ec.edu.espol.proyecto2p.modelo.Utilitarios;
import java.io.File;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;


public class Comprador {
    
    public static String NOMBRE_ARCHIVO = "Registros Comprador.txt";

    private String nombres;
    private String apellidos;
    private String correoElectronico;
    private String organizacion;
    private String correo;
    private String clave;
    private ArrayList<Oferta> oferta;

    public Comprador() {
    }

    public Comprador(String nombres, String apellidos, String correoElectronico, String organizacion, String correo, String clave) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correoElectronico = correoElectronico;
        this.organizacion = organizacion;
        this.correo = correo;
        this.clave = clave;
        this.oferta = new ArrayList<>();

    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public ArrayList<Oferta> getOferta() {
        return oferta;
    }

    public void setOferta(ArrayList<Oferta> oferta) {
        this.oferta = oferta;
    }

    public void registrarNuevoComprador() {
        ArrayList<String> correoE = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese sus nombres: ");
        String nombre = sc.nextLine();
        System.out.println("Ingrese los apellidos: ");
        String apellido = sc.nextLine();
        System.out.println("Ingrese la Organización: ");
        String organizacion = sc.nextLine();
        System.out.println("Ingrese su correo electrónico: ");
        String correoElectronico = sc.nextLine();
        boolean existeCorreo = Utilitarios.validarValorArchivo(correoElectronico, 3, Comprador.NOMBRE_ARCHIVO);
        if (existeCorreo){
            System.out.println("Un comprador con este correo ya fue registrado.");
            System.out.println("");
            opcionesComprador();
        }
       
        System.out.println("Ingrese su clave: ");
        String clave = sc.nextLine();
        clave = Utilitarios.getSHA256(clave);
        String registroComprador = nombre + "|" + apellido + "|" + organizacion + "|" + correoElectronico + "|" + clave;
        Utilitarios.saveFile(Comprador.NOMBRE_ARCHIVO, registroComprador);
        opcionesComprador();
    }
   

    public void ofertarVehiculo() {
        
        Scanner sc = new Scanner(System.in);  
        System.out.println("Ingrese su correo electronico: ");
        String c_e = sc.nextLine();
        
        System.out.println("Ingrese su clave: ");
        String contra = sc.nextLine();
        
        if(Utilitarios.validarCredenciales(c_e, contra, Comprador.NOMBRE_ARCHIVO)){
            System.out.println("Ingrese un tipo de vehiculo que desea buscar: ");
            String tipo = sc.nextLine();

            System.out.println("Ingrese un recorrido desde: ");
            String recmin = sc.nextLine();

            System.out.println("Hasta : ");
            String recmax = sc.nextLine();

            System.out.println("Ingrese un año desde: ");
            String aniomin = sc.nextLine();

            System.out.println("Hasta : ");
            String aniomax = sc.nextLine();

            System.out.println("Ingrese un precio desde: ");
            String premin = sc.nextLine();

            System.out.println("Hasta : ");
            String premax = sc.nextLine();


            ArrayList<String> cadena = Utilitarios.readFile("Registros Vehiculos.txt");

            int contador = 0;
            int opcion;
            String des;
            String ingreso;
            boolean salir = false;

            ArrayList<String> n_l = new ArrayList<>();

            for(String c: cadena){ 
                String[] val = c.split("\\|");
                //camioneta|ETR-917|Chevrolet|4x4|Gasolina|2020|4000|Blanco|Gasolina|60000|6|Automatica|Doble|vargasariel661@gmail.com
                if(recmin.isEmpty() && recmax.isEmpty()){
                    if(aniomin.isEmpty() && aniomax.isEmpty())
                        if(val[0].equals(tipo) && (Integer.parseInt(val[9])>= Integer.parseInt(premin) && Integer.parseInt(val[9])<= Integer.parseInt(premax)))
                            n_l.add(c);
                    else{
                        if(val[0].equals(tipo) && (Integer.parseInt(val[5])>= Integer.parseInt(aniomin) && Integer.parseInt(val[5])<= Integer.parseInt(aniomax)))
                            n_l.add(c);
                    }
                }else{
                    if(val[0].equals(tipo) &&(Integer.parseInt(val[6])>= Integer.parseInt(recmin) && Integer.parseInt(val[6])<= Integer.parseInt(recmax))){
                        n_l.add(c);
                    }
                } 
            }

            do{
                if(contador < n_l.size()){
                    String letter = n_l.get(contador);
                    
                    String[] val = letter.split("\\|");
                    des = "Tipo: "+tipo+", Placa: "+val[1]+", Marca: "+val[2]+", Modelo: "+val[3]+", Motor: "+val[4]+", Año: "+val[5]+", Recorrido: "+val[6]+", Color: "+val[7]+ ", Combustible: "+ val[8]+ ", Precio: "+val[9];
                    ingreso = val[1]+"|"+val[2]+"|"+val[3]+"|"+val[4]+"|"+val[5]+"|"+val[6]+"|"+val[7]+ "|"+ val[8]+"|"+val[9]+"|"+val[10]+"|"+val[11]+"|"+val[12]+"|"+c_e;
                    System.out.println(des);

                    if(contador == 0 && n_l.size() > 1){
                        System.out.println("1. Siguiente Vehiculo");
                        System.out.println("2. Realizar Oferta");
                        System.out.println("3. Regresar");
                    }
                    else if(contador == n_l.size()-1 && n_l.size() > 1){
                        System.out.println("1. Anterior Vehiculo");
                        System.out.println("2. Realizar Oferta");
                        System.out.println("3. Regresar");
                    }
                    else if(contador == n_l.size()-1){
                        System.out.println("1. Realizar Oferta");
                        System.out.println("2. Regresar");
                    }
                    else{
                        System.out.println("1. Siguiente Vehiculo");
                        System.out.println("2. Anterior Vehiculo");
                        System.out.println("3. Realizar Oferta");
                        System.out.println("4. Regresar");
                    }

                    System.out.println("Elija una opcion: ");
                    opcion = sc.nextInt();

                    if(contador == 0 && n_l.size() > 1){
                        if(opcion == 2)
                            opcion = 3;
                        else if(opcion == 3)
                            opcion = 4;
                    }
                    else if(contador == n_l.size()-1 && n_l.size() > 1){
                        if(opcion == 1)
                            opcion = 2;
                        else if(opcion == 2)
                            opcion = 3;
                        else
                            opcion = 4;
                    }
                    else if(contador == n_l.size()-1){
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
                            Oferta.guardarPrecioOfertado(ingreso);
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
            System.out.println("");
            opcionesComprador();
        }
        else{
            System.out.println("Ingrese bien los parámetros");
            opcionesComprador();
        }
        
    }
       
    public static void opcionesComprador() {
        Scanner sc = new Scanner(System.in);

        int regresar = 0;
        int opcion;
        do {
            System.out.println("1. Registrar un nuevo comprador");
            System.out.println("2. Ofertar un vehiculo");
            System.out.println("3. Regresar");

            System.out.println("Escribe la opción que deseas: ");
            opcion = sc.nextInt();
            System.out.println("");

            switch (opcion) {
                case 1:
                    Comprador c = new Comprador();
                    c.registrarNuevoComprador();
                    break;
                case 2:
                    Comprador c1 = new Comprador();
                    c1.ofertarVehiculo();
                    break;
                case 3:
                    System.out.println(" ");
                    regresar = 2;
                    break;
                default:
                    System.out.println("Solo números entre 1 y 3");

            }

        } while (regresar!=2);
        Utilitarios.menuOpciones();
    }
}
