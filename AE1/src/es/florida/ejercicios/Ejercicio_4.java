package es.florida.ejercicios;

public class Ejercicio_4 {
	public static void main(String[] args) {
		int numero = 15;
		System.out.println("Factorial de " + numero + " = " + factorial(numero));
	}
	
	public static double factorial(int num) {
		double factorial = 1;
		for (int i = 2; i <= num; i++ ) {
			factorial*=i;
		}
		return factorial;
	}
}
