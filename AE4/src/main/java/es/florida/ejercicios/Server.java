package es.florida.ejercicios;

import java.net.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.*;

public class Server{
	public static ServerSocket serverSocket;
	public static Socket server;
	public static void main(String[] args) {
		try {
			
			//Declaración de variables e inicio el servidor.
			int puerto = 1234;
			String resultado = "";
			
			serverSocket = new ServerSocket(puerto);
			// serverSocket.setSoTimeout(20000);
			while (true) {
				//Indica el puerto en el que se puede conectar un cliente
				System.out.println("Escuchando en puerto " + serverSocket.getLocalPort());
				//Si un cliente se conecta, se abre un hilo que maneja su conexión.
				server = serverSocket.accept();
				ConexionCliente cc = new ConexionCliente();
				cc.run();
				
			}
		} catch (UnknownHostException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public ServerSocket getServerSocket() {
		return serverSocket;
	}
	public Socket getServer() {
		return server;
	}


}