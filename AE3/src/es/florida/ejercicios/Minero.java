package es.florida.ejercicios;

// Minero con un constructor al que le pasamos la mina
public class Minero implements Runnable {
    protected int bolsa;
    protected int tiempoExtraccion;
    protected Mina mina;
    public static int recursosTotalesRecolectados = 0;

    public Minero(Mina mina) {
        this.mina = mina;
        this.bolsa = 0;
        this.tiempoExtraccion = 1000;
    }

    // Extraer recurso de la mina pasandole como parametro el nombre del minero
    synchronized public void extraerRecurso(String nombre) {

        while (mina.getStock() > 0) {
        	// Extrae un recurso y muestra por pantalla el stock que le queda a la mina
            mina.setStock(mina.getStock() - 1);
            System.out.println("Stock de recursos actual en la mina: " + mina.getStock() + " recusos");

            try {
                Thread.sleep(tiempoExtraccion);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Suma a la bolsa del minero el recurso extraido y muestra los recursos de la bolsa del minero
            setBolsa(getBolsa() + 1);
            System.out.println(nombre + " ha extraído de la mina " + getBolsa() + " recursos");
        }

        // El nombre del minero junto a los recursos que ha extraido
        System.out.println(nombre + " ha extraído de la mina " + getBolsa() + " recursos, con un tiempo de extracción de: " + tiempoExtraccion);
        System.err.println("SE HAN AGOTADO LOS RECURSOS DE LA MINA!");

        // Calcula los recursos totales recolectados por todo el equipo de mineros
        recursosTotalesRecolectados = recursosTotalesRecolectados + getBolsa();
        System.out.println("Recursos totales extraídos por el equipo de mineros: " + recursosTotalesRecolectados + " recursos");

    }

    // Getter bolsa
    public int getBolsa() {
        return bolsa;
    }

    // Setter bolsa
    public void setBolsa(int bolsa) {
        this.bolsa = bolsa;
    }

    // Recoger el nombre del minero y ejecutar la funcion pasandole el nombre del minero
    @Override
    public void run() {

        String nombre = Thread.currentThread().getName();
        extraerRecurso(nombre);

    }
}