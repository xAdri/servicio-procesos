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

        // Ampliación protocolo COVID-19
        // Hilo para ventilar
        Thread v1 = new Thread(new Runnable() {
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
        Thread v2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ventilador.apagarVentilador();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Iniciamos los ventiladores
        v1.start();
        v2.start();

        // Alternar el sistema de ventilacion con el apagado y encendido
        try {
            v1.join();
        } catch (InterruptedException e1) {

            e1.printStackTrace();
        }
        try {
            v2.join();
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
        
        // En el evaluable pone que tienen que encenderse y apagarse indefinidamente

    }
}