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
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) {
		
		// Pedir los datos de entrada para acceder al server
		// IP: localhost, puerto 1234
		Scanner teclado = new Scanner(System.in);
		System.out.print("Introducir IP: ");
		String host = teclado.nextLine(); 
		System.out.print("Introducir puerto: ");
		int puerto = Integer.parseInt(teclado.nextLine());
		
		try {
			// Conecta al cliente al servidor
			System.out.println("CLIENTE >> Arranca cliente -> esperando mensaje del servidor..."); 
			Socket cliente = new Socket(host,puerto);
			
			// Esta a la espera de recibir una contrasenya vacia y lo convierte a objeto contrasenya
			System.out.println("CLIENTE >> Recibe contrasenya vacia del servidor");
			ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());
			Contrasenya contrasenya = (Contrasenya) inObjeto.readObject();
			
			// Recibe los datos para realizar la encriptacion
			System.out.println("CLIENTE >> Recibe opciones para la encriptacion");
			InputStream is = cliente.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String opcionesEncriptacion = br.readLine();
			
			// Introducir la contrasenya y la settea al objeto
			System.out.print("CLIENTE >> Introduzca la contrasenya en texto plano: ");
			String stringContrasenya = teclado.nextLine(); 
			contrasenya.setContrasenyaTextoPlano(stringContrasenya);
			
			// 1 o 2 como respuesta para encriptar
			System.out.print("CLIENTE >> Indique metodo encriptacion " + opcionesEncriptacion + ": ");
			String opcion = teclado.nextLine(); 
			
			// Enviar la contrasenya escrita y luego enviar la opcion de encriptacion
			System.out.println("CLIENTE >> Envio contrasenya y opcion de encriptacion al servidor");
			ObjectOutputStream outObjeto = new ObjectOutputStream(cliente.getOutputStream());
			outObjeto.writeObject(contrasenya);
			OutputStream os = cliente.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.write(opcion + "\n");
			pw.flush();
			
			// Recinir la contrasenya encriptada y mostrarla por pantalla
			Contrasenya contrasenyaEncriptada = (Contrasenya) inObjeto.readObject();
			System.out.println("CLIENTE >> Recibida contrasenya encriptada: " + contrasenyaEncriptada.toString());
			
			cliente.close();
			teclado.close();
			
			System.out.println("CLIENTE >> Fin");
			
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("CLIENTE >> Error");
			e.printStackTrace();
		} 
		
	}

}
