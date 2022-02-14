package es.florida.ejercicios;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Ejercicio5_Cliente {

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException, ClassNotFoundException {
		// Solicitar al usuario la IP y el puerto
		Scanner teclado = new Scanner(System.in);
		System.out.println("Introducir IP: ");
		String host = teclado.nextLine();
		System.out.println("Introducir puerto: ");
		int puerto = Integer.parseInt(teclado.nextLine());
		System.out.println("CLIENTE >> Arranca cliente -> esperando mensaje del servidor...");
		
		// Con los datos pedidos creamos el socket para que el servidor y el cliente se puedan conectar
		Socket cliente = new Socket(host, puerto);
		// El servidor envía con un out primero asi que aqui hacemos un in para recibir lo que envia
		// el servidor recepcionandolo con un objeto ObjectInputStream
		ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());
		// Leeremos el objeto que llegue del servidor haciendo cast para serializarlo
		Ejercicio3_Vehiculo vehiculo = (Ejercicio3_Vehiculo) inObjeto.readObject();
		
		// Recibido y pedimos los datos del vehiculo para setearlos por teclado
		System.out.println("CLIENTE >> Recibo del servidor: " + vehiculo.getMarca() + " - " + vehiculo.getModelo());
		System.out.println("CLIENTE >> Actualizar informacion del objeto...");
		System.out.println("Introducir marca: ");
		String marca = teclado.nextLine();
		System.out.println("Introducir modelo: ");
		String modelo = teclado.nextLine();
		vehiculo.setMarca(marca);
		vehiculo.setModelo(modelo);
		Thread.sleep(2000);
		
		// El servidor está esperando asi que le mandamos con un out con el objeto vehiculo actualizado
		System.out.println("CLIENTE >> Envío del servidor: " + vehiculo.getMarca() + " - " + vehiculo.getModelo());
		ObjectOutputStream outObjeto = new ObjectOutputStream(cliente.getOutputStream());
		outObjeto.writeObject(vehiculo);
		inObjeto.close();
		outObjeto.close();
		cliente.close();
		teclado.close();
	}

}
