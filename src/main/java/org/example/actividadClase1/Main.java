package org.example.actividadClase1;

public class Main {
    static void main() {

        Thread Yes = new Yes();
        Thread No = new No();

        Yes.setPriority(Thread.MAX_PRIORITY);
        No.setPriority(Thread.MIN_PRIORITY);

        Yes.start();
        No.start();
    }

    public static class Yes extends Thread {
        @Override
        public void run() {

            int contador = 0;

            while(contador < 1000){
                System.out.println(" YES ");
                contador++;
            }
        }
    }

    public static class No extends Thread {
        @Override
        public void run() {
            int cuantasVeces = 1000;
            for (int i = 0; i <= cuantasVeces; i++) {
                System.out.println(" NO ");
            }
        }
    }
}
