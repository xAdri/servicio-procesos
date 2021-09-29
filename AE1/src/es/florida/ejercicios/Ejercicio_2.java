package es.florida.ejercicios;

import java.util.ArrayList;

public class Ejercicio_2 {
	public static void main(String[] args) {
		EjercicioA();
		EjercicioB();
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
	/**
	 * Escribe por consola las lineas consecutivas del arraylist
	 */
	public static void EjercicioB(){
		ArrayList<String> arrayList = new ArrayList<String>();
		
		arrayList.add("Adri");
		arrayList.add("David");
		arrayList.add("Manu");
		arrayList.add("Carla");
		arrayList.add("Erika");
		arrayList.add("Pedro");
		
		for (int i = 0; i < arrayList.size(); i++) {
			System.out.println(arrayList.get(i));
		}
    }
}
