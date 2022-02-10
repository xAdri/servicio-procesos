package es.florida.ejercicios;

import java.io.File;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.sun.net.httpserver.HttpServer;

public class ServidorHTTP {
	
	public static String portEmail = "587";
	public static String asunto = "AVERIA";
	public static String mensaje = "Hay una averia";
	public static String imagen = "urlimagen";
	public static String hostEmail = "smtp.gmail.com";
	public static String[] emailDestino = new String[] {"rabiaplaya@gmail.com", };
	public static String[] anexo = new String[] {"rabiaplaya@gmail.com", "mantenimientoinvernalia@gmail.com", "megustaelfresquito@gmail.com"}; 

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String host = "localhost"; // 127.0.0.1
		int puerto = 7777;
		
		InetSocketAddress direccionTCPIP = new InetSocketAddress(host, puerto);
		int backlog = 0;
		HttpServer servidor = HttpServer.create(direccionTCPIP, backlog);
		
		GestorHTTP gestorHTTP = new GestorHTTP();
		String rutaRespuesta = "/estufa";
		servidor.createContext(rutaRespuesta, gestorHTTP);
		
		
		// Opcion multihilo
		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)Executors.newFixedThreadPool(10);
		servidor.setExecutor(threadPoolExecutor);
		
		servidor.start();
		System.out.println("Servidor HTTP arranca en el puerto: http://" + host + ":" + puerto  + rutaRespuesta);
	}

}
