package org.example.actividad2;

/**
 *
 * @author Ainhoa Martínez Fernández
 *
 * Ejercicio 2) Es simplemente modificar el ejercicio 1 para que haya dos carreteras, dos muelles, y 1 ferry que carga y descarga en ambas orillas.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        // creo los muelles, uno para cada orilla
        Muelle muelleNorte = new Muelle("Norte");
        Muelle muelleSur = new Muelle("Sur");

        //creo las carreteras, una para cada orilla
        Carretera carreteraNorte = new Carretera("CARRETERA_NORTE", muelleNorte, 1, 3);
        Carretera carreteraSur = new Carretera("CARRETERA_SUR", muelleSur, 1, 3);

        // creo el ferry, que carga y descarga en el muelle norte y carga y descarga en el muelle sur
        Ferry ferry = new Ferry("FERRY", muelleNorte, muelleSur, true, "Norte/Sur", 20, 3, 3);

        //haces la carretera daemon (setDaemon), para cuando el ferry termine sus tres viajes, el programa pueda finalizar
        // aunque la carretera siga generando vehículos
        carreteraNorte.setDaemon(true);
        carreteraSur.setDaemon(true);

        //inicio los hilos
        carreteraNorte.start();
        carreteraSur.start();

        ferry.start();

        // join porque para que el hilo principal espere hasta que el hilo del ferry termine.
        ferry.join();
        //este mensaje aparece cuando ferry.join haya terminado.
        System.out.println("El ferry ha terminado su servicio.");
    }
}

