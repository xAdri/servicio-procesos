package es.florida.ejercicios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Ejercicio8_ServidorCalculo {

	public static void main(String[] args) throws IOException {
		System.err.println("SERVIDOR >> Arranca el servidor");
		ServerSocket servidor = new ServerSocket(1234);
		// While true para que el servidor siempre este escuchando
		while(true) {
			// Aceptamos la peticion del cliente
			Socket conexion = servidor.accept();
			System.err.println("SERVIDOR >> Conexion recibida!");
			
			// Recibimos y procesamos los datos que envia el cliente
			InputStream is = conexion.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			// Aqui deberiamos hacer trycatch para controlar los datos que envian por si no son correctos
			// esta hecho para leer lineas diferentes
			System.err.println("SERVIDOR >> Lee datos para la operacion");
			String linea = br.readLine();
			String num1 = br.readLine();
			String num2 = br.readLine();
			
			// Hacemos el calculo
			System.err.println("SERVIDOR >> Realiza la operacion");
			Double result = calcular(linea, num1, num2);
			
			// Devolvemos el resultado al cliente 
			System.err.println("SERVIDOR >> Devuelve resultado");
			OutputStream os = conexion.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.write(result.toString() + "\n");
			pw.flush(); // Limpiar el print writer
			System.err.println("SERVIDOR >> Espera nueva peticion");
			
		}
	}

	private static Double calcular(String op, String n1, String n2) {
		double resultado = 0;
		char simbolo = op.charAt(0);
		double num1 = Double.parseDouble(n1);
		double num2 = Double.parseDouble(n2);
		if (simbolo == '+') {resultado = num1 + num2;}
		if (simbolo == '-') {resultado = num1 - num2;}
		if (simbolo == '*') {resultado = num1 * num2;}
		if (simbolo == '/') {resultado = num1 / num2;}
		
		return resultado;
	}

}
