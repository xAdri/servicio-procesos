package es.florida.ejercicios;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class NotificacionCorreo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static void envioMail(String mensaje, String asunto, String email_remitente,String email_remitente_pass, String host_email, String port_email, String[] email_destino, String[] anexo) throws UnsupportedEncodingException, MessagingException{
		
		System.out.println(" > Remitente: " + email_remitente);
		for (int i = 0; i <email_destino.length; i++) {
			System.out.println(" > Destino: " + email_destino[i]);
		}
		System.out.println(" > Asunto: " + asunto);
		System.out.println(" > Mensaje: " + mensaje);
		
		// Propiedades para la conexion al servidor
		Properties props = System.getProperties();
		props.put("mail.smtp.host", host_email);
		props.put("mail.smtp.user", email_remitente);
		props.put("mail.smtp.clave", email_remitente_pass);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", port_email);
		
		// Crear una sesion
		Session session = Session.getDefaultInstance(props);
		
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(email_remitente));
		for(int i = 0; i < email_destino.length; i++) {
			message.addRecipients(Message.RecipientType.TO, email_destino[i]);
		}
		message.setSubject(asunto);
		
		Multipart multipart = new MimeMultipart();
		
		// Añadir las partes
		BodyPart messageBodyPart1 = new MimeBodyPart();
		messageBodyPart1.setText(mensaje);
		multipart.addBodyPart(messageBodyPart1);
		
		for(int i = 0; i < anexo.length; i++) {
			BodyPart messageBodyPartAnexo = new MimeBodyPart();
			DataSource src = new FileDataSource(anexo[0]);
			messageBodyPartAnexo.setDataHandler(new DataHandler(src));
			messageBodyPartAnexo.setFileName(anexo[0]);
		}
		
		message.setContent(multipart);
		
		// Envio del mensaje con el objeto transport
		Transport transport = session.getTransport("smtp");
		transport.connect(host_email, email_remitente, email_remitente_pass);
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
		

	}

}
