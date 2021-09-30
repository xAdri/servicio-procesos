package es.florida.ejercicios;

import java.util.Scanner;

public class Ejercicio_6 {
	public static void main(String[] args) {
	        int array[] = new int[5];
	        int sumaTotal = 0;
	        Scanner read = new Scanner(System.in);
	        
	        for(int i=0;i<5;i++) {
	            int numero = read.nextInt();
	            read.nextLine();
	            array[i] = numero;
	        }
	        
	        for(int i=array.length-1;i>-1;i--) {
	            System.out.println(array[i]);
	            sumaTotal+=array[i];
	        }
	        
	        System.out.println("Suma total = " + sumaTotal);
	        read.close();
	    }
}
