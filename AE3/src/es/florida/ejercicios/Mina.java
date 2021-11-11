package es.florida.ejercicios;

public class Mina {
	protected int stock;
	public Mina(int stock) {
		this.stock = stock;
	}
	public synchronized int getStock() {
		return stock;
	}
	public synchronized void setStock(int stock) {
		System.out.println("En la mina quedan " + stock);
		this.stock = stock;
	}
}