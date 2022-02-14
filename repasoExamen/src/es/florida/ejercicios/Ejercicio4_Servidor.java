/**
 * De manera análoga al ejemplo visto en tenría, crea una clase servudir que cada vez que
 * se conecte un cliente, le envie un objeto tipo que has creado en el ejercicio anterior
 * para que el cliente lo llene. Una vez recibido el obketo, lo deberá mostrar por pantalla.
 * Es importante que te detengas en ver que hace cada una de las instrucciones que estas utilizando.
 * Se comprueba con el siguiente ejercicio
 */
package es.florida.ejercicios;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Ejercicio4_Servidor {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		// Core del servidor
		int numeroPuerto = 1234;
		ServerSocket servidor = new ServerSocket(numeroPuerto);
		System.err.println("SERVIDOR >> Escuchando...");
		Socket cliente = servidor.accept();
		Thread.sleep(2000);
		
		// Con el cliente establecemos la comunicacion con los outobjeto (mandar) inobjeto (recibir) del cliente
		// Aqui es primero el servidor el que manda el objeto outObjeto pasandole el objeto del cliente.getOutputStream()
		ObjectOutputStream outObjeto = new ObjectOutputStream(cliente.getOutputStream());
		// Creamos el vehiculo
		Ejercicio3_Vehiculo vehiculo = new Ejercicio3_Vehiculo("Marca", "Modelo");
		// Envio del objeto a traves del socket
		outObjeto.writeObject(vehiculo);
		System.err.println("SERVIDOR >> Envio a cliente: " + vehiculo.getMarca() + " - " + vehiculo.getModelo());
		// Aqui lo recibe el cliente y se queda esperando al cliente que lo envie de vuelta
		ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());
		// Creamos un vehiculo nuevo para acceder y hacemos un cast del objeto que leemos que entra al servidor para acceder a los metodo
		// si no hacemos el (Ejercicio3_Vehiculo) no podremos utilizar getMarca()...
		Ejercicio3_Vehiculo vehiculoMod = (Ejercicio3_Vehiculo) inObjeto.readObject();
		Thread.sleep(2000);
		System.err.println("SERVIDOR >> Recibo de cliente: " + vehiculoMod.getMarca() + " - " + vehiculoMod.getModelo());
		outObjeto.close();
		inObjeto.close();
		cliente.close();
		servidor.close();
		
	}

}
