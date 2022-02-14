package es.florida.ejercicios;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Ejercicio11_ServidorCalculoMultihilo {

	public static void main(String[] args)  throws IOException{
		System.err.println("SERVIDOR >> Arranca el servidor, espera peticion");
		ServerSocket socketEscucha = null;
		try {
			socketEscucha = new ServerSocket(1234);
		} catch (IOException e) {
			System.err.println("SERVIDOR >> Error");
			return;
		}
		while (true) {
			// Cuando recibe una conexion de cliente aqui muestra el mensaje y genera un hilo
			// que le pasamos la conexion y lo lanzamos
			Socket conexion = socketEscucha.accept();
			System.err.println("SERVIDOR >> Conexion redibida --> Lanza hilo clase Peticion");
			Ejercicio11_ServidorCalculoMultihilo_hilo h = new Ejercicio11_ServidorCalculoMultihilo_hilo(conexion);
			Thread hilo = new Thread(h);
			hilo.start();
		}
	}
	
	

}
