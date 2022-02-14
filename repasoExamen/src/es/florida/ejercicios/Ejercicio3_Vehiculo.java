/**
 * Para trabajar el envio y recepcion de objetos serializados, crea una clase
 * que represente un objeto con algunos atributos, de manera análoga a la clase
 * Persona utilizada en el ejemplo de teoria. Recuerda que debe implementar
 * Serializable para que se pueda enviar a través de una conexión red
 */

package es.florida.ejercicios;

import java.io.Serializable;

// Boton derecho source generate
public class Ejercicio3_Vehiculo implements Serializable {
	String marca, modelo;
	
	public Ejercicio3_Vehiculo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ejercicio3_Vehiculo(String marca, String modelo) {
		super();
		this.marca = marca;
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	@Override
	public String toString() {
		return "Ejercicio3_Vehiculo [marca=" + marca + ", modelo=" + modelo + "]";
	}
	
	

}
