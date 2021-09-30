package es.florida.ejercicios;

import java.util.Scanner;

public class Ejercicio_8 {
    static Scanner read = new Scanner(System.in);
    
    public static void main(String[] args) {
    	long time_start, time_end;
    	
    	System.out.println("Dime el número menor: ");
        int menor = read.nextInt();
        read.nextLine();
        
        System.out.println("Dime el número mayor: ");
        int mayor = read.nextInt();
        read.nextLine();
        
    	time_start = System.currentTimeMillis();
        imprimirNumeros(menor, mayor);
    	time_end = System.currentTimeMillis();
    	System.out.println("Tiempo consumido en la ejecución: " + ( time_end - time_start ) +" milisegundos");
    }
    
    public static void imprimirNumeros(int menor, int mayor) {        
        if(menor>mayor) {
            System.out.println("Has insertado los números en orden incorrecto");
            System.exit(1);
        }
        
        for(int i=menor;i<=mayor;i++) {
            if(esPrimo(i)) {
                System.out.println(i+" es primo");
            }
            else {
                System.out.println(i+" no es primo");
            }
        }
    }
    
    public static boolean esPrimo(int numero) {
        boolean primo = true;
        
        for(int i=1;i<numero;i++) {
            if(numero%i==0&&i!=1&&i!=numero) {
                primo =false;
            }
        }
        return primo;
    }
}