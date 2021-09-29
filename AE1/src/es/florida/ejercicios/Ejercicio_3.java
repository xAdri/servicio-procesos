package es.florida.ejercicios;

public class Ejercicio_3 {
	public static void main(String[] args) {
		System.out.println("El número por parámetro es: " + args[0]);
		
		int sumaDePares = 0;
	    int hasta = Integer.parseInt(args[0]);
	    
	    for (int x = 1; x <= hasta; x++) {
	        if(x % 2 == 0){
	            sumaDePares += x;
	        }
	    }
	    
	    System.out.println(sumaDePares);
	}
}
