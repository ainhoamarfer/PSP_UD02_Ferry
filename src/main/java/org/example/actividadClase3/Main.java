package org.example.actividadClase3;

import org.example.actividad2.Muelle;

public class Main {

    static void main() throws InterruptedException {


        Thread HiloLento = new HiloLento("A", null);
        Thread HiloRapido = new HiloRapido("B", HiloLento);

        HiloRapido.start();
        HiloLento.start();

        HiloRapido.join();

        if(!HiloRapido.isAlive()){
            System.out.println("Hilo rapido muerto");
        }

        if(!HiloLento.isAlive()){
            System.out.println("Hilo lento muerto");
        }



        System.out.println("Estado vida hilo rapido: " + HiloRapido.isAlive());
        System.out.println("Estado vida hilo lento: " + HiloLento.isAlive());
        System.out.println("Ejecución terminada");
    }

    static class HiloRapido extends Thread {

        Thread hilo;
        String nombre;

        public HiloRapido(String nombre, Thread hilo) {
            this.nombre = nombre;
            this.hilo = hilo;
        }

        @Override
        public void run() {
            try {
                hilo.join();

                System.out.println("Hilo rapido vivo");
                sleep(2000);


            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


    }

    public static class HiloLento extends Thread {

        Thread hilo;
        String nombre;

        public HiloLento(String nombre, Thread hilo) {
            this.nombre = nombre;
            this.hilo = hilo;
        }

        @Override
        public void run() {
            try {
                System.out.println("Hilo lento vivo");
                sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
