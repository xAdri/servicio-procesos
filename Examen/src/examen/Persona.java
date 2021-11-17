package examen;

import java.util.List;

public class Persona implements Runnable {
	protected int colorFavorito;
	String[] arrayColores= {"Amarillo", "Verde", "Rojo", "Azul", "Naranja"};
    public static List<String> colores;

    public Persona() {
        // Aqui deberia pasarle una lista de colores pero no puedo porque no me funciona bien
    }

    // 
    synchronized public void agregarColor(String nombre) {
    	int numRandom = (int)(Math.random() * 5);
    	setColor(numRandom);
        System.out.println("Color favorito de " + nombre + " es " + getColor());
        // TODO: Añadir lista de colores que pasamos por el constructor nuestro color favorito
    }

    // Getter Color favorito
    public String getColor() {
    	return arrayColores[colorFavorito];
    }

    // Setter Color favorito
    public void setColor(int color) {
        this.colorFavorito = color;
    }

    // Recoger el nombre de la persona y ejecutar la funcion agregar color
    @Override
    public void run() {

        String nombre = Thread.currentThread().getName();
        agregarColor(nombre);

    }

}
