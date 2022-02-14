/**
 * Implementar en tu equipo las aplicaciones ServidorCalculo y ClienteCalculo
 * Prueba su funcionamiento (puedes crear un lanzador como en el ejercicio 6
 * o crear .JARs como en el 7, y haz las moficiaciones para que pueda realizar
 * cualquier tipo de operacion entre dos numeros
 */
package es.florida.ejercicios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Ejercicio8_ClienteCalculo {

	public static void main(String[] args) throws IOException {
		
		String [] operacion = {"+", "100", "50"};
		
		// Conectarnos al servidor con InetSocketAddress
		System.out.println(" >> Arranca el cliente");
		System.out.println(" >> Conexion al servidor");
		Scanner teclado = new Scanner(System.in);
		System.out.println("Intoduce IP: ");
		String host = teclado.nextLine();
		InetSocketAddress direccion = new InetSocketAddress(host, 1234);
		Socket socket = new Socket();
		socket.connect(direccion);
		
		// Enviamos los datos con pw para escritura en esa conexion de salida
		System.out.println(">> Envio de datos para el calculo: " + operacion[1] + " " + operacion[0] + " " + operacion[2]);
		PrintWriter pw = new PrintWriter(socket.getOutputStream());
		pw.print(operacion[0] + "\n");
		pw.print(operacion[1] + "\n");
		pw.print(operacion[2] + "\n");
		pw.flush();
		
		// Recibir el resultado del servidor y imprimirlo por pantalla
		System.out.println(">> Preparado el canal para recibir resultado");
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String resultado = br.readLine();
		System.out.println(">> Recibe resultado: " + resultado);
		
		socket.close();
		
	}
}
