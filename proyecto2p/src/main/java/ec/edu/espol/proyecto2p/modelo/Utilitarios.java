package ec.edu.espol.proyecto2p.modelo;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.security.MessageDigest;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;


public class Utilitarios {
    
    public static String CORREO= "proyectopoo6@gmail.com";
    public static String CORREO_PASS= "";

    public static void menuOpciones() {
        Scanner sc = new Scanner(System.in);

        boolean salir = false;

        int opcion;
        do {
            System.out.println("1. Vendedor");
            System.out.println("2. Comprador");
            System.out.println("3. Salir");

            System.out.println("Escribe la opción que deseas: ");
            opcion = sc.nextInt();
            System.out.println("");

            switch (opcion) {
                case 1:
                    Vendedor v = new Vendedor();
                    v.opcionesVendedor();
                    salir = true;
                    break;
                case 2:
                    Comprador c = new Comprador();
                    c.opcionesComprador();
                    salir = true;
                    break;
                case 3:
                    System.out.println("Salir ");
                    salir = true;
                    break;
                default:
                    System.out.println("Solo números entre 1 y 3");

            }
        } while (!salir);
    }
    

    public static boolean validarCredenciales(String correoE, String contrasena, String archivo) {
        String clave = Utilitarios.getSHA256(contrasena);

        try (Scanner sc = new Scanner(new File(archivo))) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                if (linea.contains("|")){
                    String tokens[] = linea.split("\\|");
                    
                    String correo = tokens[3];
                    String contra = tokens[4].strip();

                    if (contra.equals(clave.strip()) && correo.equals(correoE)) {
                        return true;
                    }
                }

            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo " + archivo);

        }
        return false;

    }

    public static boolean validarValorArchivo(String valor, int posicionArchivo, String archivo) {

        try (Scanner sc = new Scanner(new File(archivo))) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                if (linea.contains("|")){
                    String tokens[] = linea.split("\\|");
                    String valorArchivo = tokens[posicionArchivo];
                    if (valor.equals(valorArchivo.strip())) {
                        return true;
                    }
                }
                
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo para validacion");
        }
        return false;

    }

    public static ArrayList<String> ObtenerValoresArchivo(String valor, int posicionArchivo, String archivo) {
        ArrayList<String> lineas = new ArrayList<String>();
        try (Scanner sc = new Scanner(new File(archivo))) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                String tokens[] = linea.split("\\|");
                String valorArchivo = tokens[posicionArchivo];

                if (valor.equals(valorArchivo.strip())) {
                    //System.out.print(linea);
                    lineas.add(linea);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo para obtener valores 1.");
        }
        return lineas;

    }

    public static ArrayList<String> ObtenerValoresArchivo(String valor1, int posicionArchivo1, String valor2, int posicionArchivo2,String archivo) {
        ArrayList<String> lineas = new ArrayList<String>();
        try (Scanner sc = new Scanner(new File(archivo))) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                String tokens[] = linea.split("\\|");
                String valorArchivo1 = tokens[posicionArchivo1];
                String valorArchivo2 = tokens[posicionArchivo2];
                if (valor1.equals(valorArchivo1.strip()) && valor2.equals(valorArchivo2.strip())) {
                    lineas.add(linea);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error al leer el archivo para obtener valores 2.");
        }
        return lineas;

    }
    

    public static void eliminarValoresArchivo(String valor, int posicionArchivo, String archivo) {
        File file = new File(archivo);
        ArrayList<String> lineas = new ArrayList<String>();
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                String tokens[] = linea.split("\\|");
                String valorArchivo = tokens[posicionArchivo];

                if (!valor.equals(valorArchivo.strip())) {
                    lineas.add(linea);    
                }
            }
            
            try (PrintWriter pw = new PrintWriter(new FileWriter(file))){
                for(String linea: lineas){
                    pw.println(linea);
                } 
                pw.close();
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
            
        } catch (Exception e) {
            System.out.println("Error al leer el archivo para eliminar.");
        }

    }
    
    public static ArrayList<String> readFile(String archivo){
        ArrayList<String> lineas = new ArrayList<>();
        try (Scanner sc= new Scanner(new File (archivo))) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                String tokens[] = linea.split("\\|");
                lineas.add(linea);    
            }
        } 
        catch (Exception e) {
            System.out.println("Error al leer el archivo");
        }
        return lineas;
    } 
    

    public static void saveFile(String nomfile, String registro) {
        File file = new File(nomfile);
        
        if(!file.exists()){
            try{
                file.createNewFile();
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }  
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(file, true)))
        {
            pw.append(registro+"\n");
            pw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static String getSHA256(String input) {
        String valor = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            digest.update(input.getBytes("utf8"));
            valor = String.format("%064x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return valor;
    }
    
     public static void enviarConGMail(String destinatario, String asunto, String cuerpo) {
        String remitente = Utilitarios.CORREO;
        String claveemail = Utilitarios.CORREO_PASS;

        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");  
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.clave", claveemail);   
        props.put("mail.smtp.auth", "true");   
        props.put("mail.smtp.starttls.enable", "true"); 
        props.put("mail.smtp.port", "587"); 

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(remitente));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));  
            message.setSubject(asunto);
            message.setText(cuerpo);
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", remitente, claveemail);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (MessagingException me) {
            me.printStackTrace(); 
        }
    }
}

