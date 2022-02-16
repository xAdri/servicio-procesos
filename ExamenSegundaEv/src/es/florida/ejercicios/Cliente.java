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

	// EXAMEN
	public static void main(String[] args) {
		// IP: localhost, puerto 1234
			Scanner teclado = new Scanner(System.in);
			System.out.print("Introducir IP: ");
			String host = teclado.nextLine(); 
			System.out.print("Introducir puerto: ");
			int puerto = Integer.parseInt(teclado.nextLine());
			
			try {
				System.out.println("CLIENTE >> Arranca cliente -> esperando mensaje del servidor..."); 
				Socket cliente = new Socket(host,puerto);
				
				System.out.println("CLIENTE >> Solicita por teclado informacion del usuario");
				
				
				System.out.println("CLIENTE >> Crea un objeto con atributos usuario y contrasenya");
				ObjectOutputStream outObjeto = new ObjectOutputStream(cliente.getOutputStream());
				Usuario usuario = new Usuario("admin", "admin123");
				outObjeto.writeObject(usuario);
				System.out.println(usuario.toString());
				
				System.out.println("CLIENTE >> Recibe la Response del servidor");
				ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());
				InputStreamReader isr = new InputStreamReader(inObjeto);
				BufferedReader br = new BufferedReader(isr);
				String mensaje = br.readLine();
				System.out.println(mensaje);
				
				System.out.println("CLIENTE >> Si el mensaje es okey");
				// Solicitar por teclado al usuario el numero de lineas de texto que va a transferir
				// Enviar mensaje al servidor
				
				System.out.println("CLIENTE >> Recibe el PREPARADO del servidor");
				
				
				cliente.close();
				teclado.close();
				
				System.out.println("CLIENTE >> Fin");
				
			} catch (IOException e) {
				System.out.println("CLIENTE >> Error");
				e.printStackTrace();
			}  				
		}
}

