package es.florida.ejercicios;

import java.io.Serializable;

public class Contrasenya implements Serializable {

	private static final long serialVersionUID = 1L;  //Necesario para serializar correctamente
	String contrasenyaTextoPlano, contrasenyaEncriptada;

	public Contrasenya() {
		super();
	}

	public Contrasenya(String contrasenyaTextoPlano, String contrasenyaEncriptada) {
		super();
		this.contrasenyaTextoPlano = contrasenyaTextoPlano;
		this.contrasenyaEncriptada = contrasenyaEncriptada;
	}

	public String getContrasenyaTextoPlano() {
		return contrasenyaTextoPlano;
	}

	public void setContrasenyaTextoPlano(String contrasenyaTextoPlano) {
		this.contrasenyaTextoPlano = contrasenyaTextoPlano;
	}

	public String getContrasenyaEncriptada() {
		return contrasenyaEncriptada;
	}

	public void setContrasenyaEncriptada(String contrasenyaEncriptada) {
		this.contrasenyaEncriptada = contrasenyaEncriptada;
	}

	@Override
	public String toString() {
		return "[" + contrasenyaTextoPlano + ", " + contrasenyaEncriptada + "]";
	}
	
}
