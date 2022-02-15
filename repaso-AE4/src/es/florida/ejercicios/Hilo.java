package es.florida.ejercicios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hilo implements Runnable {

	Socket cliente;
	
	public Hilo(Socket cliente){
		this.cliente = cliente;
	}
	
	public Contrasenya encriptar(Contrasenya contrasenya, String opcion) throws NoSuchAlgorithmException {
		// Contrasenya texto plano y encriptada para trabajar con ella
		String contrasenyaTextoPlano = contrasenya.getContrasenyaTextoPlano();
		String contrasenyaEncriptada = "";
		
		// Opcion encriptacion sencilla
		if (opcion.equals("1")) {
			for (int i = 0; i < contrasenyaTextoPlano.length(); i++) {
				// Pilla el primer caracter
				char caracterActual = contrasenyaTextoPlano.charAt(i);
				int asciiCaracterActual = (int) caracterActual;
				int asciiCaracterSiguiente = asciiCaracterActual + 1;
				if (asciiCaracterSiguiente < 32 || asciiCaracterSiguiente > 126) {  // Si el caracter es no imprimible
					asciiCaracterSiguiente = 42;  //Caracter asterisco (*)
				}
				char nuevoCaracter = (char) asciiCaracterSiguiente;
				contrasenyaEncriptada = contrasenyaEncriptada + nuevoCaracter;
			}
		} else if (opcion.equals("2")) {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] resultado = md.digest(contrasenya.getContrasenyaTextoPlano().getBytes(StandardCharsets.UTF_8));
			StringBuilder sb = new StringBuilder();  //Pasar de bytes a hexadecimal en minusculas (formato habitual de presentacion de contrasenya en MD5)
		    for (byte b : resultado) {
		        sb.append(String.format("%02x", b));
		    }
			contrasenyaEncriptada = sb.toString();
		}
		contrasenya.setContrasenyaEncriptada(contrasenyaEncriptada);
		return contrasenya;
	}
	
	public void run() {
		try {
			InputStream is = cliente.getInputStream();
			
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() + " >>> Envia objeto contrasenya vacio");
			ObjectOutputStream outObjeto = new ObjectOutputStream(cliente.getOutputStream()); 
			Contrasenya contrasenya = new Contrasenya("null","null");
			outObjeto.writeObject(contrasenya);
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() + " >>> Envia opciones contrasenya al cliente");
			OutputStream os = cliente.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.write("(1 = metodo flojete, 2 = MD5)\n");
			pw.flush();
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() + " >>> Esperando respuesta cliente...");
			
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() + " >>> Recibe objeto contrasenya completado");
			ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());
			Contrasenya contrasenyaMod = (Contrasenya) inObjeto.readObject();
			
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() + " >>> Recibe opcion para la encriptacion");
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String opcion = br.readLine();
			
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() + " >>> Realiza la encriptacion");
			Contrasenya contrasenyaEncriptada = encriptar(contrasenyaMod, opcion);
			
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() + " >>> Devuelve resultado");
			outObjeto.writeObject(contrasenyaEncriptada);
			
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() + " >>> Fin del hilo");
			System.err.println("SERVIDOR >>> Espera peticion...");
			
		} catch (IOException | ClassNotFoundException | NoSuchAlgorithmException e) {
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() + " >>> Error.");
			e.printStackTrace();
		}
	}

}
