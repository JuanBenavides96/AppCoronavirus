package edu.cecar.modelo;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class  EnvioCorreoHotmail1 {		
	private static void enviarConGMail(String destinatario, String asunto, String cuerpo) {
	    // Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente también.
	    String remitente = "empresaacme2021@gmail.com";  //Para la dirección nomcuenta@gmail.com
        String clave  = "acme2021";
	    Properties props = System.getProperties();
	    props.put("mail.smtp.host", "smtp.mail.com");  //El servidor SMTP de Google
	    props.put("mail.transport.protocol","smtp");
	    props.setProperty("mail.smtp.user", remitente);
	    props.setProperty("mail.smtp.clave", clave);    //La clave de la cuenta
	    props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
	    props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
	    props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google

	    Session session = Session.getDefaultInstance(props);
	    MimeMessage message = new MimeMessage(session);

	    try {
	        message.setFrom(new InternetAddress(remitente));
	        message.addRecipients(Message.RecipientType.TO, destinatario);   //Se podrían añadir varios de la misma manera
	        message.setSubject(asunto);
	        message.setText(cuerpo);
	        Transport transport = session.getTransport("smtp");
	        transport.connect("smtp.gmail.com", remitente, clave);
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	    }
	    catch (MessagingException me) {
	        me.printStackTrace();   //Si se produce un error
	    }
	}
   
public static void main(String[] args) {
    String destinatario =  "juan.benavides@cecar.edu.co"; //A quien le quieres escribir.
    String asunto = "Correo de prueba enviado desde Java";
    String cuerpo = "Esta es una prueba de correo...";

    enviarConGMail(destinatario, asunto, cuerpo);
}

}

