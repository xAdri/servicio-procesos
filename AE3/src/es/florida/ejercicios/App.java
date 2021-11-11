package es.florida.ejercicios;

public class App {

	public static void main(String[] args) {
		Mina mina = new Mina(100);
		Minero minero = new Minero(mina);
		Minero minero2 = new Minero(mina);
		Minero minero3 = new Minero(mina);
		Minero minero4 = new Minero(mina);
		Minero minero5 = new Minero(mina);
		Minero minero6 = new Minero(mina);
		Minero minero7 = new Minero(mina);
		Minero minero8 = new Minero(mina);
		Minero minero9 = new Minero(mina);
		Minero minero10 = new Minero(mina);
		System.out.println("Trabajando...");
		minero.start();
		minero2.start();
		minero3.start();
		minero4.start();
		minero5.start();
		minero6.start();
		minero7.start();
		minero8.start();
		minero9.start();
		minero10.start();
	}

}
