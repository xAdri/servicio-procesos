package es.florida.ejercicios;

public class Ejercicio_2 {
	public static void main(String[] args) {
		EjercicioA();
	}
	/**
	 * Escribe por consola las lineas consecutivas
	 */
	public static void EjercicioA(){
		String array[] = new String[] { "Adri", "David", "Manu", "Carla", "Erika", "Pedro" };
		
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
    }
}
