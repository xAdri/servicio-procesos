package es.florida.ejercicios;

public class App {

    public static void main(String[] args) {
    	// Iniciamos la mina con los recursos que tiene y definimos el numero de mineros
        Mina mina = new Mina(100);
        int mineros = 10;
        Ventilador ventilador = new Ventilador();

        // Crear un hilo para cada minero asignandole el nombre y iniciarlo)
        for (int i = 0; i < mineros; i++) {
            Minero minero = new Minero(mina);
            Thread m;
            m = new Thread(minero);
            m.setName("Minero " + i);
            m.start();
        }

        // Hilo para ventilar
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ventilador.encenderVentilador();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Hilo para apagar el ventilador
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ventilador.apagarVentilador();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Iniciamos
        thread1.start();
        thread2.start();

        // Alternar el sistema de ventilacion con el apagado y encendido
        try {
            thread1.join();
        } catch (InterruptedException e1) {

            e1.printStackTrace();
        }
        try {
            thread1.join();
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
        
        // En el evaluable pone que tienen que encenderse y apagarse indefinidamente

    }
}