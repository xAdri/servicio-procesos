package es.florida.ejercicios;

import java.io.Serializable;

// Contraseña con los dos atributos y sus getters/setters
public class Contrasenya implements Serializable {
	String contrasenya;
	String contrasenyaEncriptada;
	public Contrasenya(String contrasenya, String contrasenyaEncriptada) {
		this.contrasenya = contrasenya;
		this.contrasenyaEncriptada = contrasenyaEncriptada;
	}
	public String getContrasenya() {
		return contrasenya;
	}
	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}
	public String getContrasenyaEncriptada() {
		return contrasenyaEncriptada;
	}
	public void setContrasenyaEncriptada(String contrasenyaEncriptada) {
		this.contrasenyaEncriptada = contrasenyaEncriptada;
	}
	
}
