/**
 * Modifica el programa anterior para que puedas descargar también imágenes y otros tipos de archivos
 * que no sean ficheros de texto. Ten en cuenta que tendras que manejar los datos como bytes, ya no
 * valdrá utilizar objetos tipo Reader que leen líneas o caracteres
 */

package es.florida.ejercicios;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Ejercicio2_DescargaTodo {

	public static void main(String[] args) {
		// Descargar un fichero de texto
		Ejercicio2_DescargaTodo gestorDescargas = new Ejercicio2_DescargaTodo();
		String url = "http://localhost:80/psp_tema4/tema4_ejercicio.txt";
		String ficheroDescargado = "ficheroDescargadoEj2.txt";
		gestorDescargas.descargarArchivo(url, ficheroDescargado);
		
		// Descargar una imagen
		url = "http://localhost:80/psp_tema4/imagen.jpg";
		ficheroDescargado = "ficheroDescargadoEj2.jpg";
		gestorDescargas.descargarArchivo(url, ficheroDescargado);
	}
	
	public void descargarArchivo(String url_descargar, String nombreArchivo) {
		System.out.println("Descargando " + url_descargar + " a " + nombreArchivo);
		
		// En funcion de la extension y el nombre del archivo descarga por txt o por bytes
		int posicionPunto = nombreArchivo.indexOf(".");
		String extension = nombreArchivo.substring(posicionPunto);
		try {
			URL url = new URL(url_descargar);
			// Canal de entrada
			InputStream is = url.openStream();
			
			if (extension.equals(".txt")) {
				// Archivo de texto
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				FileWriter fw = new FileWriter(nombreArchivo);
				String linea;
				while((linea = br.readLine()) != null) {
					fw.write(linea + "\n");
				}
				fw.close();
				br.close();
				isr.close();
				is.close();
			} else {
				// Imagen (tambien vale para texto)
				// A partir del input stream leer todo lo que hay en el input para devolverlo
				// como un array de bytes
				byte[] todo = is.readAllBytes();
				// Luego generamos un archivo de salida con el nombre del archivo, para escribirlo
				// en ese fichero debajo
				FileOutputStream fos = new FileOutputStream(nombreArchivo);
				fos.write(todo);
				fos.close();
			}
		} catch (MalformedURLException e) {
			System.out.println("URL mal escrita!");
		} catch (IOException e) {
			System.out.println("Fallo en la lectura del fichero");
		}
	}

}
