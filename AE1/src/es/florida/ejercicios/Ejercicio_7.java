package es.florida.ejercicios;

import java.util.Scanner;

public class Ejercicio_7 {
	static Scanner read = new Scanner(System.in);
	
    public static void main(String[] args) {
        System.out.println(consultarSalario());
    }
    
    public static String consultarSalario() {
        String salario = "";
        System.out.println("Como te llamas? ");
        String nombre = read.nextLine();
        
        System.out.println("Cuantos años de experiencia tienes? ");
        int tiempo = read.nextInt();
        read.nextLine();
        
        switch(tiempo) {
            case 0:
                salario = "Desarrollador Junior L1 – 15000-18000";
            case 1:
            case 2:
                salario = "Desarrollador Junior L2 – 18000-22000";
            case 3:
            case 4:
                salario = "Desarrollador Senior L1 – 22000-28000";
            case 5: 
            case 6:
            case 7: 
                salario = "Desarrollador Senior L2 – 28000-36000";
            case 8 :
                salario = "Analista / Arquitecto. Salario a convenir en base a rol";
        }
        
        if(tiempo>8) {
            salario = "Analista / Arquitecto. Salario a convenir en base a rol";
        }
        
        return salario;
    }
}
