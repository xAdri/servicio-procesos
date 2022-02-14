/**
 * Modifica el programa clientecalculo para que admita como parametros de entrada argumentos
 * los 3 parametros que necesita para hacer una peticion al servidor (tipo de operacion + - * /), n1 y n2
 * mas un string ocn el nombre que se le asignará al cliente
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

public class Ejercicio9_ClienteCalculo_v2 {

public static void main(String[] args) throws IOException {
		
		String nombreCliente = "CLIENTE";
		String [] operacion = {"+", "100", "50"};
		if(args.length > 0) {
			nombreCliente = args[0];
			// Para utilizar la multiplicacion * pillaria todo el directorio
			String tipoOperacion = args[1];
			if(tipoOperacion.length() > 1) {
				operacion[0] = tipoOperacion.substring(1);
			} else {
				operacion[0] = tipoOperacion;
			}
			operacion[1] = args[2];
			operacion[2] = args[3];
		}
		
		// Conectarnos al servidor con InetSocketAddress
		System.out.println(nombreCliente + " >> Arranca el cliente");
		System.out.println(nombreCliente + " >> Conexion al servidor");
		Scanner teclado = new Scanner(System.in);
		System.out.println("Intoduce IP: ");
		String host = teclado.nextLine();
		InetSocketAddress direccion = new InetSocketAddress(host, 1234);
		Socket socket = new Socket();
		socket.connect(direccion);
		
		// Enviamos los datos con pw para escritura en esa conexion de salida
		System.out.println(nombreCliente + " >> Envio de datos para el calculo: " + operacion[1] + " " + operacion[0] + " " + operacion[2] + " " + nombreCliente);
		PrintWriter pw = new PrintWriter(socket.getOutputStream());
		pw.print(operacion[0] + "\n");
		pw.print(operacion[1] + "\n");
		pw.print(operacion[2] + "\n");
		pw.print(nombreCliente + "\n");
		pw.flush();
		
		// Recibir el resultado del servidor y imprimirlo por pantalla
		System.out.println(nombreCliente +  " >> Preparado el canal para recibir resultado");
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String resultado = br.readLine();
		System.out.println(nombreCliente + " >> Recibe resultado: " + resultado);
		
		socket.close();
		
	}

}
