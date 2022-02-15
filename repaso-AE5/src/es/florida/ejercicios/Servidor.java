package es.florida.ejercicios;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.sun.net.httpserver.HttpServer;

public class Servidor {

    public static void main(String[] args) throws Exception {
    	
    	String host = "localhost"; //127.0.0.1
    	int puerto = 7777;
    	InetSocketAddress direccionTCPIP = new InetSocketAddress(host,puerto);
    	int backlog = 0; //Numero de conexiones pendientes que el servidor puede mantener en cola
    	HttpServer servidor = HttpServer.create(direccionTCPIP, backlog);
    	
    	GestorHTTP gestorHTTP = new GestorHTTP();   //Clase que gestionara los GETs, POSTs, etc.
    	String rutaRespuesta = "/estufa";   //Ruta (a partir de localhost en este ejemplo) en la que el servidor dara respuesta
    	servidor.createContext(rutaRespuesta, gestorHTTP);   //Crea un contexto, asocia la ruta al gestor HTTP
    	
    	ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)Executors.newFixedThreadPool(10);
    	servidor.setExecutor(threadPoolExecutor); 
    	
    	servidor.start();
    	System.out.println("Servidor HTTP arranca en el puerto " + puerto);
    	
    }

}