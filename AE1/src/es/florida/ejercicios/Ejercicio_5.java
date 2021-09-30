package es.florida.ejercicios;

public class Ejercicio_5 {
	public static void main(String[] args) {
        int array[] = new int[5];
        array = new int[] {15, 26, 45, 97, 16};
        int mayor = devolverMayor(array);
        System.out.println(mayor);
    }
	
    public static int devolverMayor(int array[]) {
        int mayor = 0;
        
        for(int i=0;i<array.length;i++) {
            if(mayor < array[i]) {
                mayor = array[i];
            }
        }
        return mayor;
    }
}
