/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyecto2p;

import java.util.Properties;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;

/**
 *
 * @author ariel
 */
public class SendCorreo {
    public static String CORREO= "";
    public static String CORREO_PASS= "";
    
    
    public static void enviarConGMail(String destinatario, String asunto, String cuerpo) {
        String remitente = SendCorreo.CORREO;
        String claveemail = SendCorreo.CORREO_PASS;
    
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
