package examen;

import java.util.ArrayList;
import java.util.List;

public class Subproceso {

	public static void main(String[] args) {
		// Pasamos el numero de personas por argumentos)
        int personas = Integer.parseInt(args[0]);;
		List<String> coloresFavoritos = new ArrayList<>();

        // Crear un hilo para cada persona asignandole el nombre y iniciarlo
        for (int i = 0; i < personas; i++) {
        	// N
            Persona persona = new Persona();
            coloresFavoritos.add(persona.getColor());
            Thread p;
            p = new Thread(persona);
            p.setName("Persona" + (i+1));
            p.start();
        }
        
        // Prints de la consola
        System.out.println("Numero de personas: " + personas);
        System.out.println("La lista de colores tiene un tamaño de: " + coloresFavoritos.size());
        
	}
}
