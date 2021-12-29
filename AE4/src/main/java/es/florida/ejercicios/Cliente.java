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
			// Declaraci�n de variables y conexi�n a servidor
			Scanner read = new Scanner(System.in);
			int puerto = 1234;
			InetAddress host = InetAddress.getByName("localhost");
			Socket socket = new Socket(host, puerto);
			System.out.println("Conectado a servidor en el puerto " + puerto);
			
			// Recibo objeto de Contrasenya vac�o
			ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
			Contrasenya contrasenyaCliente = (Contrasenya) is.readObject();
			
			
			// Relleno el campo de contrasenya con la entrada por teclado del cliente
			System.out.println("Escribe una contrase�a: ");
			String contrasenya = read.nextLine();
			contrasenyaCliente.setContrasenya(contrasenya);
			
			// Aqu� inicializo dos outputstreams, el primero para mandar un string con la elecci�n de encriptaci�n
			// y el segundo para mandar el objeto con el campo de contrasenya rellenado.
			PrintWriter paraServer = new PrintWriter(socket.getOutputStream(), true);
			ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
			os.writeObject(contrasenyaCliente);
			
			// Entrada de elecci�n de encriptaci�n
			System.out.println("\"Elige tipo de encriptaci�n:\n"
					+ "1.Revertir n�mero ASCII\n"
					+ "2.Encriptar a MD5"); 
			String eleccion = read.nextLine();
			paraServer.println(eleccion);
			
			//Vuelvo a recibir el objeto con la contrase�a encriptada y por �ltimo imprimo los datos por pantalla.
			ObjectInputStream is2 = new ObjectInputStream(socket.getInputStream());
			Contrasenya contrasenyaClienteCompleto = (Contrasenya) is.readObject();
			System.out.println("Se ha encriptado tu contrase�a:"
					+ "\n Tu contrase�a: "+contrasenyaClienteCompleto.getContrasenya()
					+ "\n Tu contrase�a encriptada: "+contrasenyaClienteCompleto.getContrasenyaEncriptada());

			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
