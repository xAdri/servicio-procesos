package es.florida.ejercicios;

// Ventilador con variables de encendido y 15 segundos de intervalo
public class Ventilador {
    protected Boolean encendido = true;
    protected int tiempo = 15000;

    // Encender el ventilador durante 15 segundos
    public void encenderVentilador() throws InterruptedException {
        while (true) {
            synchronized(this) {
                while (encendido == true)
                    wait();
                System.out.println("COVID19 seguridad: Ventilador encendido durante " + tiempo / 1000 + " segundos");
                Thread.sleep(tiempo);
                encendido = true;
                notify();
                System.out.println("Ventilador apagado");
            }
        }
    }

    // Apagar el ventilador durante 15 segundos
    public void apagarVentilador() throws InterruptedException {
        while (true) {
            synchronized(this) {
                while (encendido == false)
                    wait();
                System.err.println("COVID19 seguridad: Ventilador apagado durante " + tiempo / 1000 + " segundos");
                Thread.sleep(tiempo);
                encendido = false;
                notify();
                System.err.println("Ventilador encendido");
            }
        }
    }

}