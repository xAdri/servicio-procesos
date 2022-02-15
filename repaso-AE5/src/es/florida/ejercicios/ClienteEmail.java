package es.florida.ejercicios;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.Scanner;

import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;

public class ClienteEmail {
	
	ClienteEmail(String emailRemitente, String emailRemitentePass) {
		
		System.out.println("Envio de email");
		
		String strAsunto = "AVERIA";
		String strMensaje = "Mensaje por defecto";
		String hostEmail = "smtp.gmail.com";
		String portEmail = "587";
		String[] emailDestino = {"ohrabia@gmail.com", "ohrabia@gmail.com", };
		String[] anexo = {"error.jpg","pdf.pdf"};
		
		try {
			envioMail(strMensaje, strAsunto, emailRemitente, emailRemitentePass, hostEmail, portEmail, emailDestino, anexo);
		} catch (UnsupportedEncodingException | MessagingException e) {
			e.printStackTrace();
		}

	}

	public static void envioMail(String mensaje, String asunto, String email_remitente, String email_remitente_pass, String host_email, String port_email, String[] email_destino, String[] anexo) throws UnsupportedEncodingException, MessagingException {

		System.out.println("Envio de correo");
		System.out.println(" > Remitente: " + email_remitente);
		for (int i = 0; i <email_destino.length; i++) {
			System.out.println(" > Destino " + (i + 1) + ": " + email_destino[i]);
		}
		System.out.println(" > Asunto: " + asunto);
		System.out.println(" > Mensaje: " + mensaje); 

	    Properties props = System.getProperties();
	    props.put("mail.smtp.host", host_email);  
	    props.put("mail.smtp.user", email_remitente);
	    props.put("mail.smtp.clave", email_remitente_pass);   
	    props.put("mail.smtp.auth", "true");    
	    props.put("mail.smtp.starttls.enable", "true"); 
	    props.put("mail.smtp.port", port_email);
	    props.put("mail.smtp.ssl.trust", "smtp.gmail.com"); // Anyadir si no no deja

	    Session session = Session.getDefaultInstance(props);
	    
	    MimeMessage message = new MimeMessage(session);
	    message.setFrom(new InternetAddress(email_remitente));
	    message.addRecipients(Message.RecipientType.TO, email_destino[0]);
	    message.addRecipients(Message.RecipientType.CC, email_destino[1]);
	    message.setSubject(asunto);
	    
	    BodyPart messageBodyPart1 = new MimeBodyPart();
	    messageBodyPart1.setText(mensaje);
	    
	    BodyPart messageBodyPart2 = new MimeBodyPart();
	    DataSource src= new FileDataSource(anexo[0]);
	    messageBodyPart2.setDataHandler(new DataHandler(src));
	    messageBodyPart2.setFileName(anexo[0]);
	    
	    BodyPart messageBodyPart3 = new MimeBodyPart();
	    src= new FileDataSource(anexo[1]);
	    messageBodyPart3.setDataHandler(new DataHandler(src));
	    messageBodyPart3.setFileName(anexo[1]);
	    
	    Multipart multipart = new MimeMultipart(); 
	    multipart.addBodyPart(messageBodyPart1);
	    multipart.addBodyPart(messageBodyPart2);  
	    multipart.addBodyPart(messageBodyPart3); 
        
	    message.setContent(multipart);
	    
        Transport transport = session.getTransport("smtp");
        transport.connect(host_email, email_remitente, email_remitente_pass);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
			   
	}

}
