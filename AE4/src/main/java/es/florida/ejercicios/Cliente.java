package es.florida.ejercicios;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente implements Runnable {

	public static void main(String[] args) {
		Cliente cliente = new Cliente();
		cliente.run();
	}

	public void run() {
		try {
			// Declaración de variables y conexión a servidor
			Scanner read = new Scanner(System.in);
			int puerto = 1234;
			InetAddress host = InetAddress.getByName("localhost");
			Socket socket = new Socket(host, puerto);
			System.out.println("Conectado a servidor en el puerto " + puerto);
			
			// Recibo objeto de Contrasenya vacío
			ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
			Contrasenya contrasenyaCliente = (Contrasenya) is.readObject();
			
			
			// Relleno el campo de contrasenya con la entrada por teclado del cliente
			System.out.println("Escribe una contraseña: ");
			String contrasenya = read.nextLine();
			contrasenyaCliente.setContrasenya(contrasenya);
			
			// Aquí inicializo dos outputstreams, el primero para mandar un string con la elección de encriptación
			// y el segundo para mandar el objeto con el campo de contrasenya rellenado.
			PrintWriter paraServer = new PrintWriter(socket.getOutputStream(), true);
			ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
			os.writeObject(contrasenyaCliente);
			
			// Entrada de elección de encriptación
			System.out.println("\"Elige tipo de encriptación:\n"
					+ "1.Revertir número ASCII\n"
					+ "2.Encriptar a MD5"); 
			String eleccion = read.nextLine();
			paraServer.println(eleccion);
			
			//Vuelvo a recibir el objeto con la contraseña encriptada y por último imprimo los datos por pantalla.
			ObjectInputStream is2 = new ObjectInputStream(socket.getInputStream());
			Contrasenya contrasenyaClienteCompleto = (Contrasenya) is.readObject();
			System.out.println("Se ha encriptado tu contraseña:"
					+ "\n Tu contraseña: "+contrasenyaClienteCompleto.getContrasenya()
					+ "\n Tu contraseña encriptada: "+contrasenyaClienteCompleto.getContrasenyaEncriptada());

			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
