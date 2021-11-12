package es.florida.ejercicios;

// Mina con un constructor al que le pasamos el stock
public class Mina {
    protected int stock;

    public Mina(int stock) {
        this.stock = stock;
    }

    // Getter del stock de la mina
    public synchronized int getStock() {
        return stock;
    }

    // Setter del stock de la mina
    public synchronized void setStock(int stock) {
        this.stock = stock;
    }

}