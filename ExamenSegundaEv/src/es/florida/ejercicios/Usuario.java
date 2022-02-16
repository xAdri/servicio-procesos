package es.florida.ejercicios;

import java.io.Serializable;

public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;  //Necesario para serializar correctamente
	String nombre, contrasenya;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContrasenya() {
		return contrasenya;
	}
	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}
	
	public Usuario(String nombre, String contrasenya) {
		super();
		this.nombre = nombre;
		this.contrasenya = contrasenya;
	}
	
	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", contrasenya=" + contrasenya + "]";
	}
	
}
