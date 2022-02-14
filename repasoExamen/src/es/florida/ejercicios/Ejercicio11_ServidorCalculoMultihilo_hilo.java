package es.florida.ejercicios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Ejercicio11_ServidorCalculoMultihilo_hilo implements Runnable {

	BufferedReader br;
	PrintWriter pw;
	Socket socket;
	
	public Ejercicio11_ServidorCalculoMultihilo_hilo(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			// Leer los distintos parametros que recibimos del cliente
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			OutputStream os = socket.getOutputStream();
			pw = new PrintWriter(os);
			
			// Leer las 3 lineas para operar
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() + " >> Lee datos para la operacion");
			String operador = br.readLine();
			String num1 = br.readLine();
			String num2 = br.readLine();
			String nombreCliente = br.readLine();
			
			// Calcular
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() + " >> Cliente " + nombreCliente + " Realiza la operacion");
			Double result = calcular(operador, num1, num2);
			
			// Mostrar resultado
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() + " >> Devuelve resultado");
			pw.write(result.toString() + "\n");
			pw.flush();
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() + " >> Espera nueva peticion");
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() + " >> Error.");
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
