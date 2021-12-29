package es.florida.ejercicios;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.xml.bind.DatatypeConverter;

public class ConexionCliente implements Runnable{

	public void run() {
		try {
			
			// Inicializo las variables
			Scanner read = new Scanner(System.in);
			String resultado = "";
			
			// Creo un objeto del servidor para recibir el socket del cliente
			Server miServidor = new Server();
			Contrasenya contrasenyaCliente = new Contrasenya("","");
			
			// Mando objeto de Contrasenya vacío por el outputstream
			ObjectOutputStream os = new ObjectOutputStream(miServidor.getServer().getOutputStream());
			os.writeObject(contrasenyaCliente);
			
			// Recibo el objeto de vuelta con el campo contrasenya rellenado
			ObjectInputStream is = new ObjectInputStream(miServidor.getServer().getInputStream());
			Contrasenya contrasenyaProcesada = (Contrasenya) is.readObject();
			
			// Recibo la elección sobre el tipo de encriptación que quiere el cliente
			BufferedReader desdeCliente =new BufferedReader(new InputStreamReader(miServidor.getServer().getInputStream()));
			String line = desdeCliente.readLine();
			
			// Aqui compruebo la elección y mando el string de contrasenya al encriptador correspondiente
			if(line.equals("1")) {
				resultado = encriptarAscii(contrasenyaProcesada.getContrasenya());
			}
			if(line.equals("2")) {
				resultado = encriptarAMd5(contrasenyaProcesada.getContrasenya());
			}
			
			// Asigno el valor al objeto procesado y lo vuelvo a enviar al cliente.
			// El servidor solo puede ver la contraseña encriptada por pantalla.
			contrasenyaProcesada.setContrasenyaEncriptada(resultado);
			ObjectOutputStream os2 = new ObjectOutputStream(miServidor.getServer().getOutputStream());
			os.writeObject(contrasenyaProcesada);
			System.out.println("Contraseña procesada con éxito: " + contrasenyaProcesada.getContrasenyaEncriptada());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	 
	  /**
	   * Método que encripta un String a md5 usando una clase de la librería JAXB
	   * Es el motivo por el cual este proyecto está configurado en Maven, ya que tengo una dependencia.
	   * 
	   * @param contrasenya
	   * @return contrasenyaProcesada
	   */
	  public String encriptarAMd5(String contrasenya) {
		  byte[] bytesOfMessage;
		  String contrasenyaProcesada = null;
		try {
			  bytesOfMessage = contrasenya.getBytes("UTF-8");
			  MessageDigest md = MessageDigest.getInstance("MD5");
			  byte[] bytesOfDigest = md.digest(bytesOfMessage);
			  contrasenyaProcesada = DatatypeConverter.printHexBinary(bytesOfDigest).toLowerCase();
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contrasenyaProcesada;
	  }
	  
	  /**
	   * Método que encripta un String sumándole un valor a su numeración ASCII
	   * @param contrasenya
	   * @return resultado
	   */
	  public String encriptarAscii(String contrasenya) {
		  String resultado = "";
			for (int i = 0; i < contrasenya.length(); i++) {
				int numeroAscii = contrasenya.charAt(i);
				char miChar = (char) ((char) numeroAscii + 1);
				String caracterModificado = new String(new char[] { miChar });
				resultado = resultado + caracterModificado;
			}
		  return resultado;
	  }
	
}
