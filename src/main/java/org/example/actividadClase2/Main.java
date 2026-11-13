package org.example.actividadClase2;

public class Main {
    static void main() throws InterruptedException {


        Thread HiloDormido = new HiloDormido();

        System.out.println("Estado vida hilo: " + HiloDormido.isAlive());

        HiloDormido.start();

        System.out.println("Estado vida hilo: " + HiloDormido.isAlive());

        HiloDormido.join();

        System.out.println("Estado vida hilo: " + HiloDormido.isAlive());
    }

    public static class HiloDormido extends Thread {
        @Override
        public void run() {
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
