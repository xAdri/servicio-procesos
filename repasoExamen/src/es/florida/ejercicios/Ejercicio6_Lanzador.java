/**
 * Implementar un programa lanzador que ejecute el servidor
 * y el cliente y que muestre todas las salidas por consola
 * Falla porque no permite recoger las entradas por teclado...
 */
package es.florida.ejercicios;

import java.io.File;
import java.io.IOException;

public class Ejercicio6_Lanzador {

	public static void main(String[] args) throws IOException{
		String javaHome = System.getProperty("java.home");
		String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
		String classpath = System.getProperty("java.class.path");
		

		String className = "es.florida.ejercicios.Ejercicio4_Servidor";
		ProcessBuilder builder = new ProcessBuilder(javaBin, "-cp", classpath, className);
		builder.inheritIO().start();

		className = "es.florida.ejercicios.Ejercicio5_Cliente";
		builder = new ProcessBuilder(javaBin, "-cp", classpath, className);
		builder.inheritIO().start();

	}

}
