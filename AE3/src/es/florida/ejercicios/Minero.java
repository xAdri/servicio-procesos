package es.florida.ejercicios;

public class Minero extends Thread {
	protected int bolsa;
	protected int tiempoExtraccion;
	protected Mina mina;

	public void run() {
		try {
			extraerRecurso();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Minero(Mina mina) {
		this.bolsa = 0;
		this.tiempoExtraccion = 1000;
		this.mina = mina;
	}

	synchronized public void extraerRecurso() throws InterruptedException {
		while (mina.getStock() > 0) {
			mina.setStock(mina.getStock() - 1);
			setBolsa(getBolsa() + 1);
			Thread.sleep(tiempoExtraccion);
		}
		System.out.println("Se ha minado todo. Tengo " + getBolsa() + " materiales");
	}

	public int getBolsa() {
		return bolsa;
	}

	public void setBolsa(int bolsa) {
		this.bolsa = bolsa;
	}
}
