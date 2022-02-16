package es.florida.ejercicios;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// EXAMEN
public class Servidor {

	public static void main(String[] args) {
		System.err.println("SERVIDOR >>> Arranca el servidor, espera peticion...");
		ServerSocket socketEscucha = null;
		try {
			socketEscucha = new ServerSocket(1234);
		} catch (IOException e) {
			System.err.println("SERVIDOR >>> Error");
			e.printStackTrace();
			return;
		}
		while (true) {		
			Socket conexion;
			try {
				conexion = socketEscucha.accept();
				System.err.println("SERVIDOR >>> Conexion recibida --> Lanza hilo clase Peticion");
				Hilo h = new Hilo(conexion);
				Thread hilo = new Thread(h);
				hilo.start();
			} catch (IOException e) {
				System.err.println("SERVIDOR >>> Error");
				e.printStackTrace();
			}
		}

	}

}
