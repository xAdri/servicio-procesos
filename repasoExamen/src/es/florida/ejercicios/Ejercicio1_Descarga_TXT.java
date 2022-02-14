/**
 * Implementa para que funcione en tu equipo un gestor de descargas de ficheros de texto como
 * el visto en el ejemplo de teoría. Necesitaras tener los recursos a descargar en el 
 * directorio correspondiente de XAMPP
 */
package es.florida.ejercicios;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Ejercicio1_Descarga_TXT {

	public static void main(String[] args) {
		// Ruta desde la que quiero descargar  y fichero donde voy a descargar
		String ruta = "http://localhost:80/psp_tema4/tema4_ejercicio.txt";
		String ficheroDescargado = "ficheroDescargado.txt";
		
		System.out.println("Descargando " + ruta + " a " + ficheroDescargado);
		try {
			// Crear un objeto tipo url para luego 
			URL url = new URL(ruta);
			
			// Para recibir el recurso y voy a leer por characters necesito los stream
			InputStream is = url.openStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			
			// Para escribir el contenido en el fichero, leer linea por linea para escribirlo
			FileWriter fw = new FileWriter(ficheroDescargado);
			String linea;
			
			// Mientras la linea que leo del br no este vacia, escribimos esa linea en el fichero destino
			while ((linea = br.readLine()) != null) {
				fw.write(linea + "\n");
			}
			fw.close();
			br.close();
			isr.close();
			is.close();
			
		} catch (MalformedURLException e) {
			System.out.println("La URL está mal escrita!");
		} catch (IOException e) {
			System.out.println("Fallo en la lectura del fichero");
		}

	}

}
